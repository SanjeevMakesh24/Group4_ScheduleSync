package com.ScheduleSync.ScheduleSync;
import data.Group;
import data.TimeBlock;
import data.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
public class GroupTest {

    private User user1, user2;
    private Group group;
    private TimeBlock tb1, tb2, overlappingTb;

    @BeforeEach
    void setUp() {
        // Initialize users
        user1 = new User("user1", "pass", "user1@email.com", "username1", "User One");
        user2 = new User("user2", "pass", "user2@email.com", "username2", "User Two");

        // Initialize time blocks
        TimeBlock tb1 = new TimeBlock("Software Engineering", LocalTime.of(9, 0), LocalTime.of(10, 30),  DayOfWeek.MONDAY);
        TimeBlock tb2 = new TimeBlock("Team Meeting", LocalTime.of(11, 0), LocalTime.of(12, 0), DayOfWeek.WEDNESDAY);
        overlappingTb = new TimeBlock( "Block 3 Overlap", LocalTime.of(10, 0), LocalTime.of(11, 15), DayOfWeek.MONDAY);

        // Add time blocks to user1's schedule
        user1.getSchedule().addTimeBlock(tb1);
        user1.getSchedule().addTimeBlock(tb2);

        // Add overlapping time block to user2's schedule
        user2.getSchedule().addTimeBlock(overlappingTb);

        // Initialize group
        group = new Group("Test Group");
    }

    @Test
    void testAddMember() {
        group.addMember(user1);
        assertTrue(group.getMembers().contains(user1), "Group should contain user1 after adding.");
    }

    @Test
    void testRemoveMember() {
        group.addMember(user1);
        group.removeMember(user1);
        assertFalse(group.getMembers().contains(user1), "Group should not contain user1 after removal.");
    }

    @Test
    void testMergingNonOverlappingSchedules() {
        group.addMember(user1);
        assertEquals(2, group.getGroupSchedule().getTimeBlocks().size(), "Group schedule should contain 2 time blocks from user1.");
    }

    @Test
    void testHandlingOverlappingTimeBlocks() {
        group.addMember(user1);
        group.addMember(user2);
        // Assuming your merging logic adjusts the end time of the overlapping block
        assertTrue(group.getGroupSchedule().getTimeBlocks().stream()
                        .anyMatch(tb -> tb.getEndTime().equals(LocalTime.of(11, 15))),
                "Group schedule should adjust to include overlapping time block.");
    }
}
