package org.ericsunu;

import java.io.InputStream;
import java.util.Properties;

public class PasswordValidationRules {
    private int minLength;
    private boolean requireUppercase;
    private boolean requireLowercase;
    private boolean requireDigit;
    private boolean requireSpecial;
    private String specialCharacter;
    private int weakPasswordScore;
    private int moderatePasswordScore;
    private int scoreFactor;

    public PasswordValidationRules() {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("password-validator.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            minLength = Integer.parseInt(properties.getProperty("minLength", "8"));
            requireUppercase = Boolean.parseBoolean(properties.getProperty("requireUppercase", "true"));
            requireLowercase = Boolean.parseBoolean(properties.getProperty("requireLowercase", "true"));
            requireDigit = Boolean.parseBoolean(properties.getProperty("requireDigit", "true"));
            requireSpecial = Boolean.parseBoolean(properties.getProperty("requireSpecial", "true"));
            specialCharacter = properties.getProperty("specialCharacters");
            weakPasswordScore = Integer.parseInt(properties.getProperty("weakPasswordScore","10"));
            moderatePasswordScore = Integer.parseInt(properties.getProperty("moderatePasswordScore","14"));
            scoreFactor = Integer.parseInt(properties.getProperty("scoreFactor","2"));

        } catch (Exception e) {
            e.printStackTrace();

            minLength = 8;
            requireUppercase = true;
            requireLowercase = true;
            requireDigit = true;
            requireSpecial = true;
            specialCharacter = "!@#$%^&*()_+-=[]{}|;:'\\\",.<>?/`~";
            weakPasswordScore = 10;
            moderatePasswordScore = 14;
            scoreFactor = 2;
        }
    }

    public int getMinLength() {
        return minLength;
    }

    public boolean isRequireUppercase() {
        return requireUppercase;
    }

    public boolean isRequireLowercase() {
        return requireLowercase;
    }

    public boolean isRequireDigit() {
        return requireDigit;
    }

    public boolean isRequireSpecial() {
        return requireSpecial;
    }

    public String getSpecialCharacter() {
        return specialCharacter;
    }

    public int getWeakPasswordScore() {
        return weakPasswordScore;
    }

    public int getModeratePasswordScore() {
        return moderatePasswordScore;
    }

    public int getScoreFactor() {
        return scoreFactor;
    }
}