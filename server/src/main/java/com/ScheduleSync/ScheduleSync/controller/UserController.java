package com.ScheduleSync.ScheduleSync.controller;

import com.ScheduleSync.ScheduleSync.data.Schedule;
import com.ScheduleSync.ScheduleSync.data.TimeBlock;
import com.ScheduleSync.ScheduleSync.data.User;
import com.ScheduleSync.ScheduleSync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/addUser")
    private String saveUser(@RequestBody User user){
        userService.save(user);
        return user.getUsername();
    }


    @PostMapping(value="/{userId}/addSchedule")
    public void addScheduleToUser(@PathVariable String userId, @RequestBody Schedule newSchedule){
        User user = userService.getUserByID(userId);
        if (user != null) {
            Schedule existingSchedule = user.getSchedule();
            if (existingSchedule != null) {
                for (TimeBlock newTimeBlock : newSchedule.getTimeBlocks()) {
                    userService.addTimeBlockToUserSchedule(userId, newTimeBlock);
                }
            } else {
                //If the user doesn't have a schedule, set the new schedule
                userService.addScheduleToUser(userId, newSchedule);
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping(value="/signIn")
    public ResponseEntity<Map<String, Object>> signIn(@RequestBody Map<String, String> signInData){
        String username = signInData.get("username");
        String password = signInData.get("password");

        User user = userService.getUserByID(username);
        Map<String, Object> response = new HashMap<>();
        if (user != null && user.getPassword().equals(password)) {
            // User is logged in
            response.put("success", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Invalid username or password
            response.put("success", false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    

    @PostMapping("/{userId}/schedule/addTimeBlock")
    public void addTimeBlockToUserSchedule(@PathVariable String userId, @RequestBody TimeBlock timeBlock) {
        userService.addTimeBlockToUserSchedule(userId, timeBlock);
    }

    @GetMapping(value="/getAll")
    public Iterable<User> getUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{username}")
    private void deleteUser(@PathVariable("username") String userID){
        userService.deleteUser(userID);
    }

    @GetMapping("/search/{username}")
    private User getUser(@PathVariable(name="username") String userID){
        return userService.getUserByID(userID);
    }

    @GetMapping("/{userId}/schedule")
    public Schedule getUserSchedule(@PathVariable String userId) {
        return userService.getUserSchedule(userId);
    }

    @PostMapping("/{userId}/addFriend/{friendId}")
    public void addFriend(@PathVariable String userId, @PathVariable String friendId) {
        User user = userService.getUserByID(userId);
        User friend = userService.getUserByID(friendId);

        if (user != null && friend != null) {
            if (!user.getFriends().contains(friendId)) { // Check friendId instead of friend object
                user.addFriend(friend.getUsername()); // This now only adds the friend's userID
                friend.addFriend(user.getUsername()); // Add user to friend's list of friends
                userService.save(user);
                userService.save(friend); // Save friend's updated list of friends
            } else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Friend already added");
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User or friend not found");
        }
    }

    @GetMapping("/{userId}/friends")
    public List<Map<String, String>> getFriends(@PathVariable String userId) {
        User user = userService.getUserByID(userId);

        if (user != null) {
            List<Map<String, String>> friendsDetails = new ArrayList<>();
            for (String friendId : user.getFriends()) {
                User friend = userService.getUserByID(friendId);
                if (friend != null) {
                    Map<String, String> friendDetail = new HashMap<>();
                    friendDetail.put("userId", friend.getUsername());
                    friendDetail.put("username", friend.getUsername());
                    friendDetail.put("name", friend.getName());
                    friendsDetails.add(friendDetail);
                }
            }
            return friendsDetails;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping("/{userId}/groups")
    public Set<String> getUserGroups(@PathVariable String userId) {
        User user = userService.getUserByID(userId);
        if (user != null) {
            return user.getGroups();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found");
        }
    }


}