package com.ScheduleSync.ScheduleSync.data;

import java.time.LocalTime;
import java.util.*;

public class Group {
    private String name;
    private Set<User> members;
    private Schedule groupSchedule;

    public Group(String name) {
        this.name = name;
        this.members = new HashSet<>();
        this.groupSchedule = new Schedule();
    }

    // Add a member to the group and their schedule to the group schedule
    public void addMember(User user) {
        if (members.add(user)) { // Ensures the user isn't already a member
            mergeSchedules(user.getSchedule());
        }
    }

    public void removeMember(User user) {
      members.remove(user);
    }

    // Merge the user's schedule into the group's schedule, handling overlaps
    private void mergeSchedules(Schedule userSchedule) {
        for (TimeBlock newUserBlock : userSchedule.getTimeBlocks()) {
            boolean merged = false;
            List<TimeBlock> toRemove = new LinkedList<>();
            TimeBlock mergedBlock = new TimeBlock(newUserBlock.getBlockName(),
                    newUserBlock.getStartTime(), newUserBlock.getEndTime(),  newUserBlock.getBlockDay());

            for (TimeBlock existingBlock : groupSchedule.getTimeBlocks()) {
                if (isOverlap(existingBlock, mergedBlock)) {
                    // Extend mergedBlock to encompass existingBlock
                    mergedBlock = extendTimeBlock(mergedBlock, existingBlock);
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

    // Extend mergedBlock to include existingBlock's timeframe
    private TimeBlock extendTimeBlock(TimeBlock mergedBlock, TimeBlock existingBlock) {
        LocalTime newStart = mergedBlock.getStartTime().isBefore(existingBlock.getStartTime()) ?
                mergedBlock.getStartTime() : existingBlock.getStartTime();
        LocalTime newEnd = mergedBlock.getEndTime().isAfter(existingBlock.getEndTime()) ?
                mergedBlock.getEndTime() : existingBlock.getEndTime();
        return new TimeBlock(mergedBlock.getBlockName(), newStart, newEnd, mergedBlock.getBlockDay());
    }

    // Checks if two time blocks overlap
    private boolean isOverlap(TimeBlock block1, TimeBlock block2) {
        return block1.getBlockDay() == block2.getBlockDay() &&
                !(block1.getEndTime().isBefore(block2.getStartTime()) || block1.getStartTime().isAfter(block2.getEndTime()));
    }

    public Set<User> getMembers() {
        return this.members;
    }

    // Getter method for the group's combined schedule
    public Schedule getGroupSchedule() {
        return this.groupSchedule;
    }


}
