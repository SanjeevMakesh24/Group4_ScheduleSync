package service;

import data.Schedule;
import data.ScheduleRepository;
import data.TimeBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public void save(Schedule schedule){
        scheduleRepository.save(schedule);
    }

    // Add a TimeBlock to a specific schedule by TimeBlock ID
    public void addTimeBlock(String scheduleId, String timeBlockId) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            schedule.addTimeBlock(timeBlockId);
            scheduleRepository.save(schedule);
        } else {
            throw new RuntimeException("Schedule not found");
        }
    }

    // Remove a TimeBlock from a specific schedule by TimeBlock ID
    public void removeTimeBlock(String scheduleId, String timeBlockId) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            schedule.removeTimeBlock(timeBlockId);
            scheduleRepository.save(schedule);
        } else {
            throw new RuntimeException("Schedule not found");
        }
    }

    // Retrieve a specific schedule by ID
    public Schedule getSchedule(String scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));
        return schedule;
    }

    public void deleteSchedule(String id){
        scheduleRepository.deleteById(id);
    }

}
