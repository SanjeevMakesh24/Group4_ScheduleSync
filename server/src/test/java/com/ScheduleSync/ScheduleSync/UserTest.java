package com.ScheduleSync.ScheduleSync;

import com.ScheduleSync.ScheduleSync.data.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    //user account creation is successful
    @Test
    void testSuccessfulAccountCreation() {
        User user = new User("testuser@email.com", "myUsername", "User One", "Password12#");
        assertNotNull(user.getPassword());
        assertNotNull(user.getUsername());
        assertNotNull(user.getEmail());
    }

    //invalid: password too short
    @Test
    void passwordInvalidTooShort() {
        User user = new User("user2@email.com", "username2", "User Two", "123");
        assertNull(user.getPassword(), "Password should be null due to invalid length");
    }

    //invalid: password too long
    @Test
    void passwordInvalidTooLong() {
        User user = new User("user2@email.com", "username2", "User Two", "1234567890123");
        assertNull(user.getPassword(), "Password should be null due to invalid length");
    }

    //invalid: password has no numbers or special characters
    @Test
    void passwordInValidNoNumberAndSpecialChar() {
        User user = new User("user12@email.com", "username12", "User Twelve", "Password");
        assertNull(user.getPassword(), "Password should be invalid with at least one number and one special character");
    }

    //invalid: password exception
    @Test
    void testPasswordExceptionEmptyInput() {
        User user = new User("user3@email.com", "username3", "User Three", "");
        assertNull(user.getPassword(), "Password should be null due to being empty");
    }

    //invalid: email is invalid
    @Test
    void emailInvalid() {
        User user = new User("user4@temailscom", "username4", "User Four", "Password");
        assertNull(user.getEmail(), "Email should be null due to invalid format");
    }

    //exception: email empty input
    @Test
    void emailExceptionEmptyInput() {
        User user = new User("", "username5", "User Five", "Password");
        assertNull(user.getEmail(), "Email should be null due to being empty");
    }

    //invalid: username too short
    @Test
    void usernameInvalidTooShort() {
        User user = new User("user6@email.com", "usr", "User Six", "Password");
        assertNull(user.getUsername(), "Username should be null due to invalid length");
    }

    //invalid: username too long
    @Test
    void usernameInvalidTooLong() {
        User user = new User("user6@email.com", "usernameIsWayTooLongForThisTest", "User Six", "Password");
        assertNull(user.getUsername(), "Username should be null due to invalid length");
    }

    //exception: username empty input
    @Test
    void usernameExceptionEmptyInput() {
        User user = new User("user7@email.com", "", "User Seven", "Password");
        assertNull(user.getUsername(), "Username should be null due to being empty");
    }

    //invalid: username starts with a number
    @Test
    void testUsernameInvalidFirstCharNumber() {
        User user = new User("user8@email.com", "1username", "User Eight", "Password");
        assertNull(user.getUsername(), "Username should be null because the first character is a number");
    }

}