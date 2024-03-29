package service;

import data.TimeBlock;
import data.TimeBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeBlockService {

    @Autowired
    private TimeBlockRepository timeBlockRepository;


    // Create or update a TimeBlock
    public TimeBlock saveTimeBlock(TimeBlock timeBlock) {
        return timeBlockRepository.save(timeBlock);
    }

    // Find a TimeBlock by ID (blockName is the ID)
    public Optional<TimeBlock> findTimeBlockById(String id) {
        return timeBlockRepository.findById(id);
    }

    // Delete a TimeBlock by ID (blockName is the ID)
    public void deleteTimeBlock(String id) {
        timeBlockRepository.deleteById(id);
    }

    // List all TimeBlocks
    public List<TimeBlock> findAllTimeBlocks() {
        return timeBlockRepository.findAll();
    }


}
