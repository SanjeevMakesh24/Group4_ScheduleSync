package data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Document(collection = "schedule")
public class Schedule {
    @Setter
    @Id
    private String id; // Adding a schedule ID
    private List<String> timeBlockNames; // Changed from timeBlock to String for IDs

    public Schedule() {
        this.timeBlockNames = new ArrayList<>();
    }

    // Constructor that accepts a variable number of TimeBlock ID arguments
    public Schedule(String... timeBlockIds) {
        this.timeBlockNames = new ArrayList<>(Arrays.asList(timeBlockIds));
    }

    // Add a TimeBlock ID to the schedule
    public void addTimeBlock(String timeBlockId) {
        //might want to add conflict checking here
//        TimeBlock newTimeBlock = TimeBlockRepository.findById(timeBlockId)
//                .orElseThrow(() -> new IllegalArgumentException("TimeBlock not found"));
//
//        for (String existingId : timeBlockNames) {
//            TimeBlock existingTimeBlock = TimeBlockRepository.findById(existingId)
//                    .orElseThrow(() -> new IllegalArgumentException("TimeBlock not found"));
//
//            if (newTimeBlock.getBlockDay().equals(existingTimeBlock.getBlockDay()) &&
//                    !newTimeBlock.getEndTime().isBefore(existingTimeBlock.getStartTime()) &&
//                    !newTimeBlock.getStartTime().isAfter(existingTimeBlock.getEndTime())) {
//                throw new ConflictException("TimeBlock conflict detected");
//            }
//        }

        timeBlockNames.add(timeBlockId);
    }

    public void removeTimeBlock(String timeBlockId){
        timeBlockNames.remove(timeBlockId);
    }
}
