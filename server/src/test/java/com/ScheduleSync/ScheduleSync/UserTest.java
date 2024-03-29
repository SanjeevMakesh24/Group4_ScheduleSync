package com.ScheduleSync.ScheduleSync;


import data.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    //userID,password,email,username,name


    User testCase1;
    User testCase2 = new User("user2","xyz","testemail@email.com","anything2","John B");
    User testCase3 = new User("user3","","testemail@email.com","anything3","John C");
    User testCase4 = new User("user4","Passw0rd!","testemail@fake.com","anything4","John D");
    User testCase5 = new User("user5","Passw0rd!","","anything5","John E");

    //private User testCase6;

    @BeforeEach
    void setUp() {
        // Initialize your test case here
        testCase1 = new User("user1","Passw0rd!","testemail@email.com","anything1","John A");
    }

    @Test
    void testUserID() {
        assertEquals("user1", testCase1.getUserID(), "User ID should match");
    }

    @Test
    void testPassword() {
        assertEquals("Passw0rd!", testCase1.getPassword(), "Password should match");
    }

    @Test
    void testEmail() {
        assertEquals("testemail@email.com", testCase1.getEmail(), "Email should match");
    }

    @Test
    void testUsername() {
        assertEquals("anything1", testCase1.getUsername(), "Username should match");
    }

    @Test
    void testName() {
        assertEquals("John A", testCase1.getName(), "Name should match");
    }


}