package org.ericsunu;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
    PasswordValidationRules rules;

    public PasswordValidator() {
        this.rules = new PasswordValidationRules();
    }

    /**
     * Validates the given password based on the criteria:
     * - Not null (throws IllegalArgumentException if it is).
     * - At least 8 characters long.
     * - Contains at least one uppercase letter, one lowercase letter, one digit, and one special character.
     *
     * @param password the password to validate
     * @return <true, emptyList()></> if all criteria are met, false otherwise
     */

    public AbstractMap.SimpleEntry<Boolean, List<String>> isValid(String password) {
        List<String> errors = new ArrayList<>();
        boolean isValid  = true;
        if(!validate(password).isEmpty()) {
            isValid = false;
            errors = validate(password);
        }
        return new AbstractMap.SimpleEntry<>(isValid, errors);
    }

    public List<String> validate (String password) {
        List<String> errors = new ArrayList<>();

        if (password == null) {
            errors.add("Password cannot be null");
            return errors;
        }

        if (password.length() < rules.getMinLength()) {
            errors.add("Password is too short. The password should be at least: " + rules.getMinLength());
        }

        if (rules.isRequireUppercase() &&  password.chars().noneMatch(Character::isUpperCase)) {
            errors.add("Password must contain at least one uppercase character");
        }

        if (rules.isRequireLowercase() &&  password.chars().noneMatch(Character::isLowerCase)) {
            errors.add("Password must contain at least one lowercase character");
        }

        if (rules.isRequireDigit() &&  password.chars().noneMatch(Character::isDigit)) {
            errors.add("Password must contain at least one lowercase character");
        }

        if (rules.isRequireSpecial() &&  password.chars().noneMatch(c -> rules.getSpecialCharacter().indexOf(c) >= 0)) {
            errors.add("Password must contain at least one lowercase character");
        }

        return errors;
    }

    /**
     * Calculates the strength of a valid password.
     * This method assumes the password is non-null.
     *
     * @param password the password to evaluate
     * @return the PasswordStrength level
     */
    public PasswordStrength checkPasswordStrength(String password) {
        // Optionally, ensure the password is valid first
        if (!isValid(password).getKey()) {
            return PasswordStrength.WEAK; // Or handle invalid passwords separately
        }

        int passwordScore = 0;
        int length = password.length();

        // Length scoring
        if (length >= rules.getMinLength()) {
            passwordScore += rules.getScoreFactor();
        }
        if (length >= (rules.getMinLength() + 4)) {
            passwordScore += rules.getScoreFactor();
        }
        if (length >= (rules.getMinLength() + 2)) {
            passwordScore += rules.getScoreFactor();
        }

        // Recalculate character criteria locally
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        if (rules.isRequireUppercase() ||  password.chars().anyMatch(Character::isUpperCase)) {
            hasUpper = true;
        }

        if (rules.isRequireLowercase() ||  password.chars().anyMatch(Character::isLowerCase)) {
            hasLower = true;
        }

        if (rules.isRequireDigit() ||  password.chars().anyMatch(Character::isDigit)) {
            hasDigit = true;
        }

        if (rules.isRequireSpecial() ||  password.chars().anyMatch(c -> rules.getSpecialCharacter().indexOf(c) >= 0)) {
            hasSpecial = true;
        }

        // Add points for each condition met
        if (hasUpper) passwordScore += rules.getScoreFactor();
        if (hasLower) passwordScore += rules.getScoreFactor();
        if (hasDigit) passwordScore += rules.getScoreFactor();
        if (hasSpecial) passwordScore += rules.getScoreFactor();

        // Bonus for meeting all criteria
        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            passwordScore += rules.getScoreFactor();
        }

        // Define strength thresholds (you can adjust these as needed)
        if (passwordScore < rules.getWeakPasswordScore()) {
            return PasswordStrength.WEAK;
        } else if (passwordScore < rules.getModeratePasswordScore()) {
            return PasswordStrength.MODERATE;
        } else {
            return PasswordStrength.STRONG;
        }
    }
}