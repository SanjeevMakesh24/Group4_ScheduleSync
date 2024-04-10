package controller;

import data.Schedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ScheduleService;

@RestController
@RequestMapping("api/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

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

}
