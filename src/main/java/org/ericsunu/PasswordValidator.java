package org.ericsunu;

public class PasswordValidator {

    /**
     * Validates the given password based on the following criteria:
     * - Must not be null (throws IllegalArgumentException if it is).
     * - Must be at least 8 characters long.
     * - Must contain at least one uppercase letter.
     * - Must contain at least one lowercase letter.
     * - Must contain at least one digit.
     * - Must contain at least one special character from the set: !@#$%^&*
     *
     * @param password the password string to validate
     * @return true if the password meets all criteria, false otherwise
     */
    public boolean isValid(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        // Check minimum length
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        // Check each character of the password
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ("!@#$%^&*".indexOf(c) >= 0) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
}