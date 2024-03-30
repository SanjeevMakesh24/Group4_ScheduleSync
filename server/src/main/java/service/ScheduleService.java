package service;

import data.Schedule;
import data.ScheduleRepository;
import data.TimeBlock;
import data.TimeBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TimeBlockRepository timeBlockRepository;

    public void save(Schedule schedule){
        scheduleRepository.save(schedule);
    }

    // Add a TimeBlock to a specific schedule by TimeBlock ID
    public void addTimeBlock(String scheduleId, String timeBlockId) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        Optional<TimeBlock> timeBlockOptional = timeBlockRepository.findById(timeBlockId);

        if (scheduleOptional.isPresent() && timeBlockOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            TimeBlock timeBlock = timeBlockOptional.get();
            schedule.addTimeBlock(timeBlock);
            scheduleRepository.save(schedule);
        } else if (!scheduleOptional.isPresent()) {
            throw new RuntimeException("Schedule not found");
        } else {
            throw new RuntimeException("TimeBlock not found");
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
        return scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    // Delete a specific schedule by ID
    public void deleteSchedule(String id){
        scheduleRepository.deleteById(id);
    }
}
