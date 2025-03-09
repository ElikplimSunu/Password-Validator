package org.ericsunu;

public class PasswordValidator {
    boolean[] passwordValidityStates = new boolean[4];

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

        var passwordLength = password.length();

        // Check minimum length
        if (passwordLength < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        var passwordArray = password.toCharArray();

        // Check each character of the password
        for (char c : passwordArray) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ("!@#$%^&*()_+-=[]{}|;:'\\\",.<>?/`~".indexOf(c) >= 0) {
                hasSpecial = true;
            }
        }
        passwordValidityStates[0] = hasUpper;
        passwordValidityStates[1] = hasLower;
        passwordValidityStates[2] = hasDigit;
        passwordValidityStates[3] = hasSpecial;

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    public PasswordStrength checkPasswordStrength(String password) {
        int passwordScore = 0;

        var passwordLength = password.length();

        if (passwordLength >= 8) {
            passwordScore += 2;
        }

        if (passwordLength >= 12) {
            passwordScore += 2;
        }

        if (passwordLength >= 16) {
            passwordScore += 2;
        }

        for (boolean b : passwordValidityStates) {
            if (b) {
                passwordScore += 2;
            }
        }

        if (passwordScore >= 10 && passwordScore < 12)  {
            return PasswordStrength.WEAK;
        } else if (passwordScore >= 12 && passwordScore < 14)  {
            return PasswordStrength.MODERATE;
        } else {
            return PasswordStrength.STRONG;
        }
    }
}