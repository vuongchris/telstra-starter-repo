package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.ActuationResult;
import au.com.telstra.simcardactivator.model.SimCard;
import au.com.telstra.simcardactivator.record.SimCardRecord;
import au.com.telstra.simcardactivator.repository.SimCardRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseController {
    private final SimCardRecordRepository simCardRecordRepository;

    public DatabaseController(SimCardRecordRepository simCardRecordRepository) {
        this.simCardRecordRepository = simCardRecordRepository;
    }

    public void save(SimCard simCard, ActuationResult actuationResult) {
        SimCardRecord simCardRecord = new SimCardRecord(simCard, actuationResult);
        simCardRecordRepository.save(simCardRecord);
    }

    public SimCard querySimCard(long simCardId) {
        var simCardRecord = simCardRecordRepository.findById(simCardId).orElse(null);
        if (simCardRecord == null) {
            return null;
        }
        return new SimCard(simCardRecord);
    }
}
