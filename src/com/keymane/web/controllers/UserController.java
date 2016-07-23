package com.keymane.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keymane.domain.accounts.User;
import com.keymane.web.StandardJsonResponse;
import com.keymane.web.StandardJsonResponseImpl;

@RestController
public class UserController extends BaseController implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public StandardJsonResponse saveUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestBody @Valid com.keymane.web.rest.models.User user) {

		final StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();

		try {
			HashMap<String, Object> data = new HashMap<String, Object>();
			StandardJsonResponse jsonResponseResult = userService.saveUser(user);
			if (jsonResponseResult.isSuccess()) {
				jsonResponse.setSuccess(true, "", jsonResponseResult.getMessage());
				jsonResponse.setHttpResponseCode(HttpStatus.SC_CREATED);
				jsonResponse.setData(data);
				return jsonResponse;
			} else {
				jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
						jsonResponseResult.getMessage());
				jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
			}
			return jsonResponse;
		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
					StandardJsonResponse.SERVER_SIDE_ERROR_MSG);
			jsonResponse.setHttpResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			return jsonResponse;
		}

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public StandardJsonResponse updateUser(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestBody @Valid com.keymane.web.rest.models.User user,
			@PathVariable Long id) {
		final StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();

		try {
			HashMap<String, Object> data = new HashMap<String, Object>();
			// if (getCurrentUser() == null) {
			// jsonResponse.setSuccess(false, "",
			// StandardJsonResponse.UNAUTHORISED_REQUEST_MSG);
			// jsonResponse.setHttpResponseCode(HttpStatus.SC_FORBIDDEN);
			// return jsonResponse;
			// }

			if (user == null) {
				jsonResponse.setSuccess(false, "", StandardJsonResponse.INVALID_ARGUMENT_MSG);
				jsonResponse.setHttpResponseCode(HttpStatus.SC_BAD_REQUEST);
				return jsonResponse;
			}

			StandardJsonResponse jsonResponseResult = userService.updateUser(user, id);
			if (jsonResponseResult.isSuccess()) {
				jsonResponse.setSuccess(true, "", jsonResponseResult.getMessage());
				jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
				jsonResponse.setData(data);
				return jsonResponse;
			} else {
				jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
						jsonResponseResult.getMessage());
				jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
			}
			return jsonResponse;
		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
					StandardJsonResponse.SERVER_SIDE_ERROR_MSG);
			jsonResponse.setHttpResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			return jsonResponse;
		}

	}

	@RequestMapping(value = "/user/phone/{phoneNumber}", method = RequestMethod.POST)
	public StandardJsonResponse updateUserByPhone(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestBody @Valid com.keymane.web.rest.models.User user,
			@PathVariable String phoneNumber) {
		final StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();

		try {
			HashMap<String, Object> data = new HashMap<String, Object>();
			// if (getCurrentUser() == null) {
			// jsonResponse.setSuccess(false, "",
			// StandardJsonResponse.UNAUTHORISED_REQUEST_MSG);
			// jsonResponse.setHttpResponseCode(HttpStatus.SC_FORBIDDEN);
			// return jsonResponse;
			// }

			if (user == null) {
				jsonResponse.setSuccess(false, "", StandardJsonResponse.INVALID_ARGUMENT_MSG);
				jsonResponse.setHttpResponseCode(HttpStatus.SC_BAD_REQUEST);
				return jsonResponse;
			}

			StandardJsonResponse jsonResponseResult = userService.updateUserByPhone(user, phoneNumber);
			if (jsonResponseResult.isSuccess()) {
				jsonResponse.setSuccess(true, "", jsonResponseResult.getMessage());
				jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
				jsonResponse.setData(data);
				return jsonResponse;
			} else {
				jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
						jsonResponseResult.getMessage());
				jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
			}
			return jsonResponse;
		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
					StandardJsonResponse.SERVER_SIDE_ERROR_MSG);
			jsonResponse.setHttpResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			return jsonResponse;
		}

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public StandardJsonResponse getUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable Long id) {

		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();

		try {

			User user = userService.getUserById(id);

			if (user == null) {
				jsonResponse.setHttpResponseCode(HttpStatus.SC_NOT_FOUND);
				jsonResponse.setSuccess(false, "Resource not found", StandardJsonResponse.RESOURCE_NOT_FOUND_MSG);
				return jsonResponse;

			} else {
				HashMap<String, Object> responseData = new HashMap<String, Object>();
				responseData.put("user", convert(user));
				jsonResponse.setSuccess(true);
				jsonResponse.setData(responseData);
				jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
				return jsonResponse;
			}

		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
					StandardJsonResponse.SERVER_SIDE_ERROR_MSG);
			jsonResponse.setHttpResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "/user/phone/{phoneNumber}", method = RequestMethod.GET)
	public StandardJsonResponse getUserByPhoneNumber(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable String phoneNumber) {

		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();

		try {

			User user = userService.getUserByMainPhone(phoneNumber);

			if (user == null) {
				jsonResponse.setHttpResponseCode(HttpStatus.SC_NOT_FOUND);
				jsonResponse.setSuccess(false, "Resource not found", StandardJsonResponse.RESOURCE_NOT_FOUND_MSG);
				return jsonResponse;

			} else {
				HashMap<String, Object> responseData = new HashMap<String, Object>();
				responseData.put("user", convert(user));
				jsonResponse.setSuccess(true);
				jsonResponse.setData(responseData);
				jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
				return jsonResponse;
			}

		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
					StandardJsonResponse.SERVER_SIDE_ERROR_MSG);
			jsonResponse.setHttpResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}
		return jsonResponse;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public StandardJsonResponse getUsers(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();

		try {

			List<User> dbUsers = userService.getAllUsers();

			if (dbUsers.size() <= 0) {
				jsonResponse.setHttpResponseCode(HttpStatus.SC_NOT_FOUND);
				jsonResponse.setSuccess(false, "Resource not found", StandardJsonResponse.RESOURCE_NOT_FOUND_MSG);
				return jsonResponse;

			} else {
				HashMap<String, Object> responseData = new HashMap<String, Object>();
				List<com.keymane.web.rest.models.User> users = new ArrayList<com.keymane.web.rest.models.User>();

				for (User dbUser : dbUsers) {
					users.add(convert(dbUser));
				}
				responseData.put("users", users);
				jsonResponse.setSuccess(true);
				jsonResponse.setData(responseData);
				jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
				return jsonResponse;
			}

		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
					StandardJsonResponse.SERVER_SIDE_ERROR_MSG);
			jsonResponse.setHttpResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}
		return jsonResponse;
	}

}
