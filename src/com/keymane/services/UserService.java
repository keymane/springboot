package com.keymane.services;

import java.util.List;
import java.util.Map;

import com.keymane.domain.accounts.User;
import com.keymane.web.StandardJsonResponse;
import com.keymane.web.StandardResponseImpl;
import com.keymane.web.rest.models.Login;

public interface UserService {

	StandardResponseImpl saveUser(com.keymane.web.rest.models.User user);

	StandardResponseImpl validatePhoneNumber(String phoneNumber);

	User getUserByMainPhone(String phoneNumber);

	StandardResponseImpl validateEmail(String email);

	User getUserByEmail(String email);

	StandardResponseImpl updateUser(com.keymane.web.rest.models.User user, Long id);
	
	StandardResponseImpl updateUserByPhone(com.keymane.web.rest.models.User user, String phoneNumber);

	StandardResponseImpl validatePassword(String Password, String email);

	StandardResponseImpl validateName(String name);

	User getUserById(Long id);

	Map<String, Object> saveNewUserRecord(User user);

	User fetchUser(Long userId);

	Map<String, Object> updateUserRecord(User user, String email, String firstName, String lastName, String oldPassword,
			String newPassword, String mainPhone, Integer nationalId);

	StandardJsonResponse authenticate(Login login);
	
	List<User> getAllUsers();
}
