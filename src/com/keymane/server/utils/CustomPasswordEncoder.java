package com.keymane.server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.keymane.domain.accounts.User;
import com.keymane.services.UserService;
import com.keymane.util.AccountUtils;

@Component
public class CustomPasswordEncoder {

	@Autowired
	UserService userService;

	public String encodePassword(String rawPass, Object salt) {
		if (salt instanceof String) {
			User user = findUserByEmail((String) salt);

			return AccountUtils.getDoubleHashedPassword(user.getId(), rawPass, (String) salt);
		}
		return null;
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {

		if (salt instanceof String) {

			User user = findUserByEmail((String) salt);

			if (user != null && user.getEncryptedPassword().equals(encPass))
				return AccountUtils.getDoubleHashedPassword(user.getId(), rawPass, (String) salt).equals(encPass);
		}

		return false;
	}

	public User findUserByEmail(String userName) {

		User springUser = userService.getUserByEmail(userName);

		return springUser;
	}

}
