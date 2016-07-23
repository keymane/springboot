package com.keymane.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.keymane.domain.accounts.User;
import com.keymane.services.UserService;

public class BaseController {

	@Autowired
	protected UserService userService;

	protected final Log logger = LogFactory.getLog(getClass());

	protected com.keymane.web.rest.models.User convert(User dbUser) {
		com.keymane.web.rest.models.User user = new com.keymane.web.rest.models.User();
		user.setId(dbUser.getId());
		user.setEmail(dbUser.getEmail());
		user.setFirstName(dbUser.getFirstName());
		user.setLastName(dbUser.getLastName());
		user.setNationalId(dbUser.getNationalId());
		user.setPhoneNumber(dbUser.getPhoneNumber());

		return user;
	}

}
