package com.ScheduleSync.ScheduleSync.service;

import com.ScheduleSync.ScheduleSync.data.Schedule;
import com.ScheduleSync.ScheduleSync.data.TimeBlock;
import com.ScheduleSync.ScheduleSync.data.User;
import com.ScheduleSync.ScheduleSync.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void save(User user){
        userRepo.save(user);
    }


    public void deleteUser(String ID){
        userRepo.deleteById(ID);
    }

    public User getUserByID(String userID){
        return userRepo.findById(userID).get();
    }

    public Iterable<User> getAllUsers(){
        return this.userRepo.findAll();
    }

    public void addScheduleToUser(String userId, Schedule schedule) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (user != null) {
            user.setSchedule(schedule);
            userRepo.save(user);
        } else {
            throw new RuntimeException("Cannot update schedule. User with id " + userId + " does not exist.");
        }
    }
    public void addTimeBlockToUserSchedule(String userId, TimeBlock timeBlock) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Schedule userSchedule = user.getSchedule();
        userSchedule.addTimeBlock(timeBlock);
        userRepo.save(user);
    }

    public Schedule getUserSchedule(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getSchedule();
    }



}