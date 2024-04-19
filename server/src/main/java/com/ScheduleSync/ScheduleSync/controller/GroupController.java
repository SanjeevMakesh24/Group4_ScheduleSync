package com.ScheduleSync.ScheduleSync.controller;

import com.ScheduleSync.ScheduleSync.data.Event;
import com.ScheduleSync.ScheduleSync.data.Group;
import com.ScheduleSync.ScheduleSync.data.Schedule;
import com.ScheduleSync.ScheduleSync.data.TimeBlock;
import com.ScheduleSync.ScheduleSync.service.EventService;
import com.ScheduleSync.ScheduleSync.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private EventService eventService;

    //Group CRUD
    @PostMapping("/create")
    public String createGroup(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        Group group = groupService.createGroup(name);
        return group.getName();
    }

    @GetMapping("/{groupId}")
    public Group getGroup(@PathVariable String groupId) {
        return groupService.getGroup(groupId);
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable String groupId) {
        groupService.deleteGroup(groupId);
    }

    //Group Member CRUD
    @PostMapping("/{groupId}/addMember/{userId}")
    public void addMember(@PathVariable String groupId, @PathVariable String userId) {
        groupService.addMember(groupId, userId);
    }

    @DeleteMapping("/{groupId}/removeMember/{userId}")
    public void removeMember(@PathVariable String groupId, @PathVariable String userId) {
        groupService.removeMember(groupId, userId);
    }

   //group schedule CRUD

    @PostMapping("/{groupId}/addEvent/{eventId}")
    public void addEventToGroupSchedule(@PathVariable String groupId, @PathVariable String eventId) {
        Group group = groupService.getGroup(groupId);
        Event event = eventService.getEventById(eventId);
        groupService.addEventToGroupSchedule(group, event);
    }

    @DeleteMapping("/{groupId}/removeEvent/{eventId}")
    public void removeEventFromGroupSchedule(@PathVariable String groupId, @PathVariable String eventId) {
        Group group = groupService.getGroup(groupId);
        Event event = eventService.getEventById(eventId);
        //groupService.removeEventFromGroupSchedule(group, event);
        group.removeEvent(event);
        removeTimeBlockFromGroupSchedule(groupId, event.getTimeBlock().getBlockName());
    }

    @DeleteMapping("/{groupId}/removeTimeBlock/{blockName}")
    public void removeTimeBlockFromGroupSchedule(@PathVariable String groupId, @PathVariable String blockName) {
        Group group = groupService.getGroup(groupId);
        Schedule schedule = group.getSchedule();
        TimeBlock timeBlockToRemove = null;
        for (TimeBlock timeBlock : schedule.getTimeBlocks()) {
            if (blockName.equals(timeBlock.getBlockName())) {
                timeBlockToRemove = timeBlock;
                break;
            }
        }
        if (timeBlockToRemove != null) {
            group.removeTimeBlockFromSchedule(timeBlockToRemove);
            groupService.saveGroup(group);
        }
    }

    @GetMapping("/{groupId}/schedule")
    public Schedule getGroupSchedule(@PathVariable String groupId) {
        return groupService.getGroupSchedule(groupId);
    }

    @GetMapping("/{groupId}/members")
    public Set<String> getGroupMembers(@PathVariable String groupId) {
        Group group = groupService.getGroup(groupId);
        if (group != null) {
            return group.getGroupMemberNames();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Group not found");
        }
    }


}