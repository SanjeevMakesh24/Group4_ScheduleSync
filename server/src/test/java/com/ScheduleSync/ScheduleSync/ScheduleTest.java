package com.ScheduleSync.ScheduleSync;

import com.ScheduleSync.ScheduleSync.data.Schedule;
import com.ScheduleSync.ScheduleSync.data.TimeBlock;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    //test for successfully adding time blocks to the schedule
    @Test
    public void addingNonConflictingTimeBlocks() {
        TimeBlock tb1 = new TimeBlock("Software Engineering", LocalTime.of(9, 0), LocalTime.of(10, 30),  DayOfWeek.MONDAY);
        TimeBlock tb2 = new TimeBlock("Team Meeting", LocalTime.of(11, 0), LocalTime.of(12, 0), DayOfWeek.WEDNESDAY);
        Schedule schedule = new Schedule();

        schedule.addTimeBlock(tb1);
        schedule.addTimeBlock(tb2);

        assertEquals(2, schedule.getTimeBlocks().size(), "Schedule should contain 2 time blocks.");
    }

    //testing for schedule conflicts
    @Test
    public void addingConflictingTimeBlocks() {
        TimeBlock tb1 = new TimeBlock("Database Class", LocalTime.of(10, 0), LocalTime.of(11, 30), DayOfWeek.MONDAY);
        TimeBlock tb2 = new TimeBlock("Algorithms Class", LocalTime.of(11, 0), LocalTime.of(12, 30), DayOfWeek.MONDAY);
        Schedule schedule = new Schedule();

        schedule.addTimeBlock(tb1); //this should be added successfully.
        schedule.addTimeBlock(tb2); //this should not be added due to conflict.

        assertEquals(1, schedule.getTimeBlocks().size(), "Schedule should contain only 1 time block due to conflict.");
    }

    //testing removing from a schedule
    @Test
    public void testRemovingTimeBlock() {
        TimeBlock tb1 = new TimeBlock("UI Design", LocalTime.of(13, 0), LocalTime.of(14, 30),  DayOfWeek.TUESDAY);
        Schedule schedule = new Schedule();
        schedule.addTimeBlock(tb1);

        schedule.removeTimeBlock("UI Design");

        assertTrue(schedule.getTimeBlocks().isEmpty(), "Schedule should be empty after removing the time block.");
    }

}
