package com.keymane.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;

public class AccountUtils {
	static final Log logger = LogFactory.getLog(AccountUtils.class.getName());

	public static final Long THE_PRIME = 15485867L;

	public static final String THE_SALT_APPEND = ".com.keymane.crypto.";

	public static String getHashedPassword(String password, String salt) {

		String hashedPassword = null;

		try {

			String inputString = "com.keymane." + password + ".crypto";
			hashedPassword = new Sha256Hash(inputString, salt, 1).toBase64();

		} catch (Exception e) {
			logger.error("exception ", e);
		}

		return hashedPassword;

	}

	public static String getDoubleHashedPassword(Long userId, String password, String salt) {

		String hashedPassword = getHashedPassword(password, salt);
		return getDoubleHashedPasswordFromHash(userId, hashedPassword);
	}

	public static String getDoubleHashedPasswordFromHash(Long userId, String hashedPassword) {

		String afterSha512 = null;

		try {
			Long theModulus = THE_PRIME % userId;
			String theSalt = theModulus + THE_SALT_APPEND + userId;

			afterSha512 = new Sha512Hash(String.valueOf(hashedPassword), theSalt, 5).toBase64();

		} catch (Exception e) {
			logger.error("exception ", e);
		}

		return afterSha512;
	}
}
