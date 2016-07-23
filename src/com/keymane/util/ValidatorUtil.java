package com.keymane.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
	
	public static String emailRegex = "[_A-Za-z0-9\\-\\+]+(\\.[_A-Za-z0-9\\-]+)*@[A-Za-z0-9\\-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
	public static String nameRegex = "\\w{2,}";
	public static String phoneNumberRegex = "\\d{10,16}";
	public static String passwordRegex = ".{8,}^\\S*$";
	public static String registrationNumberRegex = "\\w{4,10}";
	public static String alphanumericRegex   = "^[a-zA-Z0-9]*$";
	
	/**
	 * Validates that the given value is a valid email address.
	 * 
	 * @param value
	 * 
	 * @return true if valid email, otherwise false
	 */
	public static boolean validateEmailAddress(String email){
		
		boolean isValidEmailAddress = false;
		
		try {
			isValidEmailAddress = isRegexMatch(emailRegex, email);
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		
		return isValidEmailAddress;		
	}
	
	/**
	 * Validates that the given value is more than 2 character long
	 * 
	 * @param value
	 * 
	 * @return true if valid name, otherwise false
	 */
	public static boolean validateName(String name){
		
		boolean isValidName = false;
		
		try {
			isValidName = isRegexMatch(nameRegex, name);
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		
		return isValidName;		
	}
	
	/**
	 * Validates that the given value is 10 to 16 digits long
	 * 
	 * @param value
	 * 
	 * @return true if valid phone, otherwise false
	 */
	public static boolean validatePhone(String number){
		
		boolean isValidPhoneNumber = false;
		
		try {
			isValidPhoneNumber = isRegexMatch(phoneNumberRegex, number);
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		
		return isValidPhoneNumber;		
	}
	
	/**
	 * Validates that the given value is more than 8 numeric characters long
	 * 
	 * @param value
	 * 
	 * @return true if valid password, otherwise false
	 */
	public static boolean validatePassword(String password){
		
		boolean isValidPassword = false;
		
		try {
			isValidPassword = isRegexMatch(passwordRegex, password);
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		
		return isValidPassword;		
	}
	
	public static boolean isRegexMatch(String regex, String value) {

		try {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(value);
			boolean valid = m.matches();

			if (valid)
				return true;

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Validates that the given value is 4 to 10 digits long
     * 
     * @param value
     * 
     * @return true if valid registration, otherwise false
     */
    public static boolean validateRegistration(String registration){
        
        boolean isValidRegistration = false;
        
        try {
            isValidRegistration = isRegexMatch(registrationNumberRegex, registration);
        } catch (Exception e) {         
            e.printStackTrace();
            return false;
        }
        
        return isValidRegistration;      
    }
    
    /**
     * Validates that the given value is Alphanumeric
     * 
     * @param value
     * 
     * @return true if valid value, otherwise false
     */
    public static boolean validateAlphanumeric(String value){
        
        boolean isValidValue = false;
        
        try {
            isValidValue = isRegexMatch(alphanumericRegex, value);
        } catch (Exception e) {         
            e.printStackTrace();
            return false;
        }
        
        return isValidValue;      
    }

}
