package com.ScheduleSync.ScheduleSync.data;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;
import java.util.*;

public class Group {

    @Id
    @Getter
    private String name;
    @Getter
    private Set<User> members;
    // Getter method for the group's combined schedule
    @Getter
    private Schedule groupSchedule;
    private List<Event> events;

    public Group(String name) {
        this.name = name;
        this.members = new HashSet<>();
        this.groupSchedule = new Schedule();
        this.events = new ArrayList<>();
    }

    // Add a member to the group and their schedule to the group schedule
    public void addMember(User user, String userName) {
        if (members.add(user)) { // Ensures the user isn't already a member
            mergeSchedules(user.getSchedule(), userName);
        }
    }

    private void mergeSchedules(Schedule userSchedule, String userName) {
        for (TimeBlock newUserBlock : userSchedule.getTimeBlocks()) {
            boolean merged = false;
            List<TimeBlock> toRemove = new LinkedList<>();
            TimeBlock mergedBlock = new TimeBlock(userName + ": " + newUserBlock.getBlockName(),
                    newUserBlock.getStartTime(), newUserBlock.getEndTime(),  newUserBlock.getBlockDay());

            for (TimeBlock existingBlock : groupSchedule.getTimeBlocks()) {
                if (isOverlap(existingBlock, mergedBlock)) {
                    // Extend mergedBlock to encompass existingBlock
                    mergedBlock = extendTimeBlock(mergedBlock, existingBlock, userName);
                    toRemove.add(existingBlock);
                    merged = true;
                }
            }

            groupSchedule.getTimeBlocks().removeAll(toRemove); // Remove all overlapping blocks
            if (merged || toRemove.isEmpty()) {
                // Add the extended mergedBlock or the new block if no overlap was found
                groupSchedule.getTimeBlocks().add(mergedBlock);
            }
        }
    }

    private TimeBlock extendTimeBlock(TimeBlock mergedBlock, TimeBlock existingBlock, String userName) {
        LocalTime newStart = mergedBlock.getStartTime().isBefore(existingBlock.getStartTime()) ?
                mergedBlock.getStartTime() : existingBlock.getStartTime();
        LocalTime newEnd = mergedBlock.getEndTime().isAfter(existingBlock.getEndTime()) ?
                mergedBlock.getEndTime() : existingBlock.getEndTime();
        String newName = userName + ": " + existingBlock.getBlockName();
        return new TimeBlock(newName, newStart, newEnd, mergedBlock.getBlockDay());
    }

    // Checks if two time blocks overlap
    private boolean isOverlap(TimeBlock block1, TimeBlock block2) {
        return block1.getBlockDay() == block2.getBlockDay() &&
                !(block1.getEndTime().isBefore(block2.getStartTime()) || block1.getStartTime().isAfter(block2.getEndTime()));
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    public void addEventToSchedule(Event event) {
        this.groupSchedule.addTimeBlock(event.getTimeBlock());
    }


    public Schedule getSchedule() {
        return groupSchedule;
    }

    public void removeTimeBlockFromSchedule(TimeBlock timeBlock) {
        this.groupSchedule.removeTimeBlock(timeBlock.getBlockName());
    }

    public Set<String> getGroupMemberNames(){
        Set<String> memberNames = new HashSet<>();
        for(User member : members){
            memberNames.add(member.getName());
        }
        return memberNames;
    }
}
