package com.keymane.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keymane.data.accounts.UserMapper;
import com.keymane.domain.accounts.User;
import com.keymane.domain.accounts.UserExample;
import com.keymane.server.utils.CustomPasswordEncoder;
import com.keymane.util.ValidatorUtil;
import com.keymane.web.StandardJsonResponse;
import com.keymane.web.StandardResponseImpl;
import com.keymane.web.rest.models.Login;

@Service
public class UserServiceImpl extends AbstractBaseServiceProvider implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	CustomPasswordEncoder customPasswordEncoder;

	@Override
	@Transactional
	public StandardResponseImpl saveUser(com.keymane.web.rest.models.User user) {

		StandardResponseImpl result = new StandardResponseImpl();
		try {
			if (user == null)
				return null;

			result = validateName(user.getFirstName());
			if (!result.isSuccess()) {
				return result;
			}

			result = validateName(user.getLastName());
			if (!result.isSuccess()) {
				return result;
			}

			result = validatePhoneNumber(user.getPhoneNumber());
			if (!result.isSuccess()) {
				return result;
			}

			result = validateEmail(user.getEmail());
			if (!result.isSuccess()) {
				return result;
			}

			result = validatePassword(user.getPassword(), user.getEmail());
			if (!result.isSuccess()) {
				return result;
			}

			User dbUser = new User();
			dbUser.setFirstName(user.getFirstName());
			dbUser.setLastName(user.getLastName());
			dbUser.setEmail(user.getEmail());
			dbUser.setPhoneNumber(user.getPhoneNumber());
			dbUser.setNationalId(user.getNationalId());
			dbUser.setEncryptedPassword(user.getPassword());

			Map<String, Object> saveUserResult = saveNewUserRecord(dbUser);

			if (saveUserResult != null && saveUserResult.get("success") == Boolean.TRUE) {
				result.setSuccess(true, "", "The User Account has been created successfully.");
			} else {
				result.setSuccess(false, "", "An error occurred while attempting to register the account.");
			}

			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("exception", e);
			return result;
		}
	}

	@Override
	public StandardResponseImpl updateUser(com.keymane.web.rest.models.User user, Long id) {

		StandardResponseImpl result = new StandardResponseImpl();
		try {
			if (user == null || id == null)
				return null;

			result = validateName(user.getFirstName());
			if (!result.isSuccess()) {
				return result;
			}

			result = validateName(user.getLastName());
			if (!result.isSuccess()) {
				return result;
			}

			boolean isValidPhone = ValidatorUtil.validatePhone(user.getPhoneNumber());
			if (!isValidPhone) {
				result.setSuccess(false, "", "Phone Number should be valid");
				return result;
			}

			boolean isValidEmail = ValidatorUtil.validateEmailAddress(user.getEmail());
			if (!isValidEmail) {
				result.setSuccess(false, "", "Email should be valid");
				return result;
			}

			Map<String, Object> updateUserResult = updateUserRecord(getUserById(id), user.getEmail(),
					user.getFirstName(), user.getLastName(), user.getPassword(), user.getNewPassword(),
					user.getPhoneNumber(), user.getNationalId());

			if (updateUserResult != null && updateUserResult.get("success") == Boolean.TRUE) {
				result.setSuccess(true, "", "The User Account has been updated successfully.");
			} else {
				result.setSuccess(false, "", "An error occurred while attempting to update the account.");
			}

			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("exception", e);
			return result;
		}
	}

	@Override
	public StandardResponseImpl updateUserByPhone(com.keymane.web.rest.models.User user, String phoneNumber) {

		StandardResponseImpl result = new StandardResponseImpl();
		try {
			if (user == null || phoneNumber == null)
				return null;

			result = validateName(user.getFirstName());
			if (!result.isSuccess()) {
				return result;
			}

			result = validateName(user.getLastName());
			if (!result.isSuccess()) {
				return result;
			}

			boolean isValidPhone = ValidatorUtil.validatePhone(user.getPhoneNumber());
			if (!isValidPhone) {
				result.setSuccess(false, "", "Phone Number should be valid");
				return result;
			}

			boolean isValidEmail = ValidatorUtil.validateEmailAddress(user.getEmail());
			if (!isValidEmail) {
				result.setSuccess(false, "", "Email should be valid");
				return result;
			}

			Map<String, Object> updateUserResult = updateUserRecord(getUserByMainPhone(phoneNumber), user.getEmail(),
					user.getFirstName(), user.getLastName(), user.getPassword(), user.getNewPassword(),
					user.getPhoneNumber(), user.getNationalId());

			if (updateUserResult != null && updateUserResult.get("success") == Boolean.TRUE) {
				result.setSuccess(true, "", "The User Account has been updated successfully.");
			} else {
				result.setSuccess(false, "", "An error occurred while attempting to update the account.");
			}

			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("exception", e);
			return result;
		}
	}

	@Override
	public StandardResponseImpl validatePhoneNumber(String phoneNumber) {

		StandardResponseImpl result = new StandardResponseImpl();

		try {

			// check if it is a valid name
			boolean isValidPhoneNumber = ValidatorUtil.validatePhone(phoneNumber);

			if (!isValidPhoneNumber) {
				result.setSuccess(false, "", "Phone Number should be 10 to 16 digits in length");
				return result;
			}

			// check whether number is already in use by another account

			User driverWithSamePhoneNumber = getUserByMainPhone(phoneNumber);

			if (driverWithSamePhoneNumber != null) {
				result.setSuccess(false, "", "Phone Number is already in use by another account");
				return result;
			}

			result.setSuccess(true, "", "");
			return result;

		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
			return result;
		}

	}

	@Override
	public User getUserByMainPhone(String phoneNumber) {

		if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
			return null;
		}

		try {
			List<User> users = new ArrayList<>();

			UserExample example = new UserExample();
			example.createCriteria().andPhoneNumberEqualTo(phoneNumber);

			users = userMapper.selectByExample(example);

			if (users != null && users.size() > 0) {
				return users.get(0);
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}

	}

	@Override
	public StandardResponseImpl validateName(String name) {

		StandardResponseImpl result = new StandardResponseImpl();

		try {

			// check if it is a valid name
			boolean isValidName = ValidatorUtil.validateName(name);

			if (!isValidName) {
				result.setSuccess(false, "", "Name should be more than 2 characters long");
				return result;
			}

			result.setSuccess(true, "", "");
			return result;

		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
			return result;
		}

	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public StandardResponseImpl validateEmail(String email) {

		StandardResponseImpl result = new StandardResponseImpl();
		try {

			if (email == null || email.trim().isEmpty()) {
				result.setSuccess(false, "", "");
				return result;
			}

			// check if it is a valid email address
			boolean isValidEmailAddress = ValidatorUtil.validateEmailAddress(email);

			if (!isValidEmailAddress) {
				result.setSuccess(false, "", "Email should be valid");
				return result;
			}

			// check whether email is already in use by another account
			User driverWithSameEmail = getUserByEmail(email);

			if (driverWithSameEmail != null) {
				result.setSuccess(false, "",
						"The specified email address is already associated with an existing account. Please enter a different email address");
				return result;
			}

			result.setSuccess(true, "", "");
			return result;

		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
			return result;
		}

	}

	@Override
	public User getUserByEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			return null;
		}

		try {

			UserExample example = new UserExample();
			example.createCriteria().andEmailEqualTo(email);

			List<User> users = userMapper.selectByExample(example);

			if (users != null && users.size() > 0) {
				return users.get(0);
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	@Override
	public StandardResponseImpl validatePassword(String password, String email) {

		StandardResponseImpl result = new StandardResponseImpl();
		try {

			if (password == null || password.trim().isEmpty()) {
				result.setSuccess(false, "", "");
				return result;
			}

			// check that password does not contain spaces
			if (password.contains(" ")) {
				result.setSuccess(false, "", "Password should not contain spaces");
				return result;
			}

			// check that password is atleast 8 characters long
			if (password.length() < 8) {
				result.setSuccess(false, "", "Password should be atleast 8 characters long");
				return result;
			}

			// check that password is not the same as email field
			if (password.equalsIgnoreCase(email)) {
				result.setSuccess(false, "", "Password should not be the same as email");
				return result;
			}

			result.setSuccess(true, "", "");
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
			return result;
		}

	}

	@Override
	public User getUserById(Long id) {

		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> saveNewUserRecord(User user) {

		Map<String, Object> result = new HashMap<String, Object>();

		try {

			String salt = user.getEmail();
			String unencryptedPassword = user.getEncryptedPassword();
			user.setSalt(salt);
			// user.setIsDeleted(false);
			user.setCreatedAt(new Date());

			userMapper.insertSelectiveAndInjectId(user);

			String passwdDigest = customPasswordEncoder.encodePassword(unencryptedPassword, salt);
			user.setEncryptedPassword(passwdDigest);
			user.setUpdatedAt(new Date());
			userMapper.updateByPrimaryKeySelective(user);

			result.put("success", true);
			result.put("user", user);
			result.put("message", "The User Account has been created successfully.");
			return result;

		} catch (Exception e) {

			logger.error("exception", e);

			result.put("success", false);
			result.put("user", user);
			result.put("message", "An exception occurred while attempting to register the account.");
			return result;
		}
	}

	@Override
	public Map<String, Object> updateUserRecord(User user, String email, String firstName, String lastName,
			String oldPassword, String newPassword, String mainPhone, Integer nationalId) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {

			if (user == null) {
				result.put("success", false);
				result.put("message", "The User Account does not exist.");
			}

			if (user != null) {
				if (!user.getEmail().equals(email)) {
					user.setEmail(email);
					user.setUpdatedAt(new Date());
					userMapper.updateByPrimaryKey(user);

					String passwdDigest = customPasswordEncoder.encodePassword(oldPassword, user.getEmail());
					user.setEncryptedPassword(passwdDigest);
					user.setSalt(user.getEmail());

				}
				user.setEmail(email);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setPhoneNumber(mainPhone);
				user.setNationalId(nationalId);

				if (newPassword != null && !newPassword.isEmpty() && oldPassword != null && !oldPassword.isEmpty()) { // only
																														// do
					// authenticated.. change only if user has password
					if (customPasswordEncoder.isPasswordValid(user.getEncryptedPassword(), oldPassword,
							user.getEmail())) {

						// If password has not been altered so no need to
						// re-hash it
						if (newPassword != null && !newPassword.isEmpty() && !customPasswordEncoder
								.isPasswordValid(user.getEncryptedPassword(), newPassword, user.getEmail())) {
							String passwdDigest = customPasswordEncoder.encodePassword(newPassword, user.getEmail());
							user.setEncryptedPassword(passwdDigest);
							user.setSalt(user.getEmail());
						}
					} else {
						result.put("success", false);
						result.put("message", "Old password does not match");
						return result;
					}
				}
				// In case the email is changed, check that it does not
				// exist
				if (!user.getEmail().equals(email)) {
					if (checkExistingEmail(email)) {
						result.put("success", false);
						result.put("message", "There already exists another account registered using " + email + ".");
						return result;
					}
					user.setEmail(email);
				}
				user.setUpdatedAt(new Date());
				userMapper.updateByPrimaryKey(user);
			}
			result.put("success", true);
			result.put("message", "The User Account has been updated successfully.");
			return result;

		} catch (Exception e) {
			result.put("success", false);
			result.put("message", "An exception occurred while attempting to register the account.");
			logger.error("exception", e);
			return result;
		}
	}

	public boolean checkExistingEmail(String email) {
		try {
			User user = getUserByEmail(email);
			return user != null;

		} catch (Exception e) {
			logger.error("An exception occurred while attempting to check your credentials.", e);
			return false;
		}
	}

	@Override
	public User fetchUser(Long userId) {

		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public StandardJsonResponse authenticate(Login login) {

		StandardResponseImpl result = new StandardResponseImpl();

		User user = getUserByEmail(login.getUsername());

		if (user == null) {
			result.setSuccess(false, "", "Incorrect User or Password");
			return result;
		}

		if (!customPasswordEncoder.isPasswordValid(user.getEncryptedPassword(), login.getPassword(), user.getSalt())) {
			logger.debug("Authentication failed: password does not match stored value");

			result.setSuccess(false, "", "Incorrect User or Password");
			return result;
		}

		user.setLastLogin(new Date());
		userMapper.updateByPrimaryKeySelective(user);

		result.setSuccess(true, "", "Login Successfull");
		return result;

	}

	@Override
	public List<User> getAllUsers() {

		List<User> users = userMapper.selectByExample(new UserExample());
		return users;
	}

}
