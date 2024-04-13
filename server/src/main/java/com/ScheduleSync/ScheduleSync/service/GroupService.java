package com.ScheduleSync.ScheduleSync.service;

import com.ScheduleSync.ScheduleSync.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public Group createGroup(String name) {
        Group group = new Group(name);
        return groupRepository.save(group);
    }

    public void addMember(String groupId, String userId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        group.addMember(user);
        groupRepository.save(group);
    }

    public void removeMember(String groupId, String userId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        group.removeMember(user);
        groupRepository.save(group);
    }

    public Group getGroup(String groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public void addEventToGroupSchedule(Group group, Event event) {
        Schedule schedule = group.getSchedule();
        TimeBlock timeBlock = event.getTimeBlock();
        schedule.addTimeBlock(timeBlock);
        group.addEvent(event);
        groupRepository.save(group);
    }

    public void removeEventFromGroupSchedule(Group group, Event event) {
        Schedule schedule = group.getSchedule();
        TimeBlock timeBlock = event.getTimeBlock();
        schedule.removeTimeBlock(timeBlock.getBlockName());
        group.removeEvent(event);
        groupRepository.save(group);
    }

    public void deleteGroup(String groupId) {
        groupRepository.deleteById(groupId);
    }

    public Schedule getGroupSchedule(String groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        return group.getGroupSchedule();
    }

    public void saveGroup(Group group) {
        groupRepository.save(group);
    }
}