package com.nnk.springboot.Utils;

import java.util.regex.Pattern;

public class PasswordValidator {


    /**
     * CHECK form entry for the password (at least one capital letter, at least 8 characters, at least 1 digit and one symbol)
     */
    public static boolean validatePassword(String password) {
        // Check for at least one capital letter
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return false;
        }

        // Check for minimum length of 8 characters
        if (password.length() < 8) {
            return false;
        }

        // Check for at least 1 digit
        if (!Pattern.compile("\\d").matcher(password).find()) {
            return false;
        }

        // Check for at least 1 symbol
        if (!Pattern.compile("[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\-=]").matcher(password).find()) {
            return false;
        }

        // All criteria are met
        return true;
    }
}

