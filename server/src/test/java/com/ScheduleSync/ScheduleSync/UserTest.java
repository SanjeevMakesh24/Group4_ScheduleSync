package com.ScheduleSync.ScheduleSync;

import controller.UserController;
import data.User;

public class UserTest {
    //userID,password,email,username,name
    User testCase1 = new User("user1","Passw0rd!","testemail@email.com","anything1","John A");
    User testCase2 = new User("user2","xyz","testemail@email.com","anything2","John B");
    User testCase3 = new User("user3","","testemail@email.com","anything3","John C");
    User testCase4 = new User("user4","Passw0rd!","testemail@fake.com","anything4","John D");
    User testCase5 = new User("user5","Passw0rd!","","anything5","John E");


}