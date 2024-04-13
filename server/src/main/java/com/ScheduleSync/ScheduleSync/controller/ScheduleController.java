package com.ScheduleSync.ScheduleSync.controller;

import com.ScheduleSync.ScheduleSync.data.Schedule;
import com.ScheduleSync.ScheduleSync.data.TimeBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ScheduleSync.ScheduleSync.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping(value="/addSchedule")
    public String saveSchedule(@RequestBody Schedule schedule){
        scheduleService.save(schedule);
        return schedule.getId();
    }

    @RequestMapping("/search/{id}")
    public Schedule getSchedule(@PathVariable(name="id") String scheduleID){
        return scheduleService.getSchedule(scheduleID);
    }

    @RequestMapping("/delete/{id}")
    public void deleteSchedule(@PathVariable(name="id") String scheduleID){
        scheduleService.deleteSchedule(scheduleID);
    }

    @PostMapping("/{scheduleId}/addTimeBlock")
    public void addTimeBlockToSchedule(@PathVariable String scheduleId, @RequestBody String timeBlock) {
        scheduleService.addTimeBlock(scheduleId, timeBlock);
    }

}
