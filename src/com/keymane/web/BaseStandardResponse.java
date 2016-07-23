package com.keymane.web;

import java.util.HashMap;

public abstract class BaseStandardResponse implements StandardJsonResponse {
	private boolean success = false;

	private HashMap<String, String> messages;

	private HashMap<String, String> errors;

	private HashMap<String, Object> data;

	private String targetUrl;

	private int httpResponseCode;

	public BaseStandardResponse() {

		messages = new HashMap<String, String>();
		errors = new HashMap<String, String>();
		data = new HashMap<String, Object>();

		// messages.put(DEFAULT_MSG_NAME_FIELD, DEFAULT_MSG_NAME_VALUE);
		// messages.put(DEFAULT_MSG_TITLE_FIELD, DEFAULT_MSG_TITLE_VALUE);
	}

	/**
	 * @param successthe
	 *            success to set if success is false a default message and title is added
	 */
	@Override
	public void setSuccess(boolean success) {
		this.success = success;
		if (!success) {
			messages.put(DEFAULT_MSG_NAME_FIELD, DEFAULT_MSG_NAME_VALUE);
			messages.put(DEFAULT_MSG_TITLE_FIELD, DEFAULT_MSG_TITLE_VALUE);
		}
	}

	/**
	 * @return the success
	 */
	@Override
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param targetUrl
	 *            the targetUrl to set
	 */
	@Override
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	/**
	 * @return the targetUrl
	 */
	@Override
	public String getTargetUrl() {
		return targetUrl;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	@Override
	public void setMessages(HashMap<String, String> messages) {
		this.messages = messages;
	}

	/**
	 * @return the messages
	 */
	@Override
	public HashMap<String, String> getMessages() {
		return messages;
	}

	@Override
	public String getMessage() {
		return messages.get(DEFAULT_MSG_NAME_FIELD);
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	@Override
	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	/**
	 * @return the errors
	 */
	@Override
	public HashMap<String, String> getErrors() {
		return errors;
	}

	@Override
	public HashMap<String, Object> getData() {
		return data;
	}

	@Override
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

	/**
	 * @param success
	 * @param title
	 *            - message title
	 * @param message
	 *            -message body
	 */
	@Override
	public void setSuccess(boolean success, String title, String message) {
		this.success = success;
		messages.put(DEFAULT_MSG_NAME_FIELD, (message == null || message.isEmpty()) ? "" : message);
		messages.put(DEFAULT_MSG_TITLE_FIELD, (title == null || title.isEmpty()) ? "" : title);
	}

	@Override
	public void addMessage(String message) {
		messages.put(DEFAULT_MSG_NAME_FIELD, message);
	}

	@Override
	public void setHttpResponseCode(int code) {
		httpResponseCode = code;
	}

	@Override
	public int getHttpResponseCode() {
		return httpResponseCode;
	}
}