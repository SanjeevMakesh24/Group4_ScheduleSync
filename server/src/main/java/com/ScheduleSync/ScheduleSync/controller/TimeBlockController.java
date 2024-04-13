package com.ScheduleSync.ScheduleSync.controller;

import com.ScheduleSync.ScheduleSync.data.TimeBlock;
import com.ScheduleSync.ScheduleSync.service.TimeBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/timeblock")
public class TimeBlockController {

    @Autowired
    private TimeBlockService timeBlockService;

    @PostMapping("/create")
    public TimeBlock createTimeBlock(@RequestBody TimeBlock timeBlock) {
        return timeBlockService.saveTimeBlock(timeBlock);
    }

    @GetMapping("/{id}")
    public Optional<TimeBlock> getTimeBlock(@PathVariable String id) {
        return timeBlockService.findTimeBlockById(id);
    }

    @PutMapping("/update/{id}")
    public TimeBlock updateTimeBlock(@PathVariable String id, @RequestBody TimeBlock updatedTimeBlock) {
        Optional<TimeBlock> timeBlock = timeBlockService.findTimeBlockById(id);
        if (timeBlock.isPresent()) {
            TimeBlock existingTimeBlock = timeBlock.get();
            existingTimeBlock.setBlockName(updatedTimeBlock.getBlockName());
            existingTimeBlock.setStartTime(updatedTimeBlock.getStartTime());
            existingTimeBlock.setEndTime(updatedTimeBlock.getEndTime());
            existingTimeBlock.setBlockDay(updatedTimeBlock.getBlockDay());
            return timeBlockService.saveTimeBlock(existingTimeBlock);
        } else {
            throw new RuntimeException("TimeBlock not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTimeBlock(@PathVariable String id) {
        timeBlockService.deleteTimeBlock(id);
    }

    @GetMapping("/all")
    public List<TimeBlock> getAllTimeBlocks() {
        return timeBlockService.findAllTimeBlocks();
    }
}