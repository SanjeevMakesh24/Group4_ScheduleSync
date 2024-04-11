package com.ScheduleSync.ScheduleSync.data;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "schedule")
@Getter
public class Schedule {

    @Id
    private String id;
    private List<TimeBlock> timeBlocks;

    public Schedule() {
        this.timeBlocks = new ArrayList<>();
    }

    // Add a TimeBlock object to the schedule, with conflict checking
    public void addTimeBlock(TimeBlock newTimeBlock) {
        for (TimeBlock existingBlock : timeBlocks) {
            if (isConflict(newTimeBlock, existingBlock)) {
                //If there's a conflict, you might log it, handle it accordingly, or throw an exception
                return; // Indicates a conflict was detected
            }
        }
        timeBlocks.add(newTimeBlock);
    }

    public void removeTimeBlock(String timeBlockId) {
        timeBlocks.removeIf(block -> block.getBlockName().equals(timeBlockId));
    }

    // Simple conflict check (example based on overlapping times in the same day)
    private boolean isConflict(TimeBlock newBlock, TimeBlock existingBlock) {
        return newBlock.getBlockDay() == existingBlock.getBlockDay() &&
                !newBlock.getEndTime().isBefore(existingBlock.getStartTime()) &&
                !newBlock.getStartTime().isAfter(existingBlock.getEndTime());
    }
}
