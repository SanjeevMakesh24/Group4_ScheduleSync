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
    public TimeBlock addTimeBlock(TimeBlock newTimeBlock) {
        if (timeBlocks.isEmpty()) {
            timeBlocks.add(newTimeBlock);
            return newTimeBlock;
        }

        for (TimeBlock existingBlock : timeBlocks) {
            if (isConflict(newTimeBlock, existingBlock)) {
                // If there's a conflict, return the existing block
                return existingBlock;
            }
        }

        timeBlocks.add(newTimeBlock);
        return newTimeBlock; // Return the newly added block if there's no conflict
    }

    public void removeTimeBlock(String timeBlockId) {
        timeBlocks.removeIf(block -> block.getBlockName() != null && block.getBlockName().equals(timeBlockId));
    }

    // Simple conflict check (example based on overlapping times in the same day)
    private boolean isConflict(TimeBlock newBlock, TimeBlock existingBlock) {
        return newBlock.getBlockDay() == existingBlock.getBlockDay() &&
                !newBlock.getEndTime().isBefore(existingBlock.getStartTime()) &&
                !newBlock.getStartTime().isAfter(existingBlock.getEndTime());
    }
}
