package com.ScheduleSync.ScheduleSync.controller;

import com.ScheduleSync.ScheduleSync.data.Schedule;
import com.ScheduleSync.ScheduleSync.data.User;
import com.ScheduleSync.ScheduleSync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/addUser")
    private String saveUser(@RequestBody User user){
        userService.save(user);
        return user.getUserID();
    }

    @PostMapping(value="/{userId}/addSchedule")
    public void addScheduleToUser(@PathVariable String userId, @RequestBody Schedule schedule){
        userService.addScheduleToUser(userId, schedule);
    }

    @GetMapping(value="/getAll")
    public Iterable<User> getUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{userID}")
    private void deleteUser(@PathVariable("userID") String userID){
        userService.deleteUser(userID);
    }

    @RequestMapping("/search/{userID}")
    private User getUser(@PathVariable(name="userID") String userID){
        return userService.getUserByID(userID);
    }


}