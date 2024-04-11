package com.ScheduleSync.ScheduleSync.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Document(collection = "timeBlock")
@Data //takes care of all the getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class TimeBlock {


    //private String id;
    @Id
    private String blockName;
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek blockDay;
}
