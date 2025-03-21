package org.ericsunu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {
    PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    @DisplayName("Should Validate A Valid Password")
    void shouldValidate() {
        // Our test password--it has to be a valid password since we are testing the happy-path of the method
        var password = "PASSword@123";

        // This is the size of the errors list that we are expecting the validate() to return
        // It will have a size of 0 since we don't expect any errors with the password
        var expectedSize = 0;

        // This stores the returned list of errors that the validate() returns
        // We expect the list to be empty since our password is valid.
        var errors = passwordValidator.validate(password);

        // This is the actual size of the returned list
        var actualSize = errors.size();

        // It should be true that our errors list is empty. This is how the assertion will pass else it will fail.
        assertTrue(errors.isEmpty());

        // It should be true that the error list size is 0. This is how the assertion will pass else it will fail.
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("Should Validate Invalid Password")
    void shouldValidateInvalid() {
        var invalidPassword1 = "passwor";
        var invalidPassword2 = "PASSWOR";
        var minLengthError = "Password is too short. The password should be at least: 8";
        var upperCaseError = "Password must contain at least one uppercase character";
        var lowerCaseError = "Password must contain at least one lowercase character";
        var numberError = "Password must contain at least one number";
        var specialCharacterError = "Password must contain at least one special character";
        var nullPasswordError = "Password cannot be null";

        var errorsForPassword1 = passwordValidator.validate(invalidPassword1);
        assertEquals(4, errorsForPassword1.size());

        errorsForPassword1.forEach((error) -> {
            if(error.equals(minLengthError)) {
                assertTrue(true,  minLengthError);
            }
            if(error.equals(upperCaseError)) {
                assertTrue(true,  upperCaseError);
            }
            if(error.equals(numberError)) {
                assertTrue(true,  numberError);
            }
            if(error.equals(specialCharacterError)) {
                assertTrue(true,  specialCharacterError);
            }
        });

        var errorsForPassword2 = passwordValidator.validate(invalidPassword2);
        assertEquals(4, errorsForPassword2.size());

        // TODO: Add assertions for errors for invalid password 2

        var errorsForNullPassword = passwordValidator.validate(null);
        assertEquals(1, errorsForNullPassword.size());

        errorsForNullPassword.forEach(error -> assertEquals(nullPasswordError, error, error));
    }

    @Test
    @DisplayName("Password Should Be Valid")
    void shouldBeValid() {
        var validPassword = "PASSword@123";

        var isValid = passwordValidator.isValid(validPassword);

        var isValidBoolean = isValid.getKey();

        var errorList = isValid.getValue();

        var errorsListExpectedSize = 0;

        var errorsListActualSize = errorList.size();

        assertTrue(isValidBoolean, "Password is invalid");
        assertEquals(errorsListExpectedSize, errorsListActualSize, "Password is invalid");
    }

    //TODO: Add edge case or exception case test for the isValid()

    //TODO: Add edge case or exception case test for the checkPasswordStrength()
}