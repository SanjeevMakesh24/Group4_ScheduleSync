package com.ScheduleSync.ScheduleSync;

import data.Schedule;
import data.TimeBlock;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    public void Test1(){
        TimeBlock tb1 = new TimeBlock("Software Engineering", LocalTime.of(9, 0), LocalTime.of(10, 30), DayOfWeek.MONDAY);
        TimeBlock tb2 = new TimeBlock("Team Meeting", LocalTime.of(11, 0), LocalTime.of(12, 0), DayOfWeek.WEDNESDAY);
        Schedule schedule1 = new Schedule(tb1.getBlockName(), tb2.getBlockName());
        // Invocation
        List<String> names = schedule1.getTimeBlockNames();

        List<String> expectedNames = Arrays.asList("Software Engineering", "Team Meeting");
        assertEquals(expectedNames, names, "The names should match the expected list.");
    }


}
