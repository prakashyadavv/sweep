package com.example.demo.controller;

import com.example.demo.model.FormData;
import com.example.demo.model.FormDataHistory;
import com.example.demo.repository.FormDataRepository;
import com.example.demo.repository.FormDataHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FormDataController {

    @Autowired
    private FormDataRepository formDataRepository;

    @Autowired
    private FormDataHistoryRepository formDataHistoryRepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveFormData(@RequestBody FormData formData) {
        // Check if record already exists
        if (formDataRepository.existsById(formData.getFromAccount())) {
            return ResponseEntity.status(400).body("Sweep record already exists");
        }

        try {
            if ("Inactive".equals(formData.getStatus())) {
                // Determine the next record key
                Integer maxRecordKey = formDataHistoryRepository.findMaxRecordKeyByFromAccount(formData.getFromAccount());
                Integer nextRecordKey = maxRecordKey + 1;

                FormDataHistory historyEntry = new FormDataHistory();
                historyEntry.setFromAccount(formData.getFromAccount());
                historyEntry.setRecordKey(nextRecordKey);
                historyEntry.setAction(formData.getAction());
                historyEntry.setSweepDay(formData.getSweepDay());
                historyEntry.setToAccount(formData.getToAccount());
                historyEntry.setResultantBalance(formData.getResultantBalance());
                historyEntry.setStatus(formData.getStatus());

                formDataHistoryRepository.save(historyEntry);
                formDataRepository.deleteById(formData.getFromAccount());
            } else {
                // Just save in smdt
                formDataRepository.save(formData);
            }
            return ResponseEntity.ok("Data processed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to process data");
        }
    }

    @PutMapping("/update/{fromAccount}")
    public ResponseEntity<String> updateFormData(@PathVariable String fromAccount, @RequestBody FormData formData) {
        if (!formDataRepository.existsById(fromAccount)) {
            return ResponseEntity.status(404).body("Record not found");
        }

        try {
            // If status is inactive, move to smdh and delete from smdt
            if ("Inactive".equals(formData.getStatus())) {
                Integer maxRecordKey = formDataHistoryRepository.findMaxRecordKeyByFromAccount(fromAccount);
                Integer nextRecordKey = maxRecordKey + 1;

                FormDataHistory historyEntry = new FormDataHistory();
                historyEntry.setFromAccount(fromAccount);
                historyEntry.setRecordKey(nextRecordKey);
                historyEntry.setAction(formData.getAction());
                historyEntry.setSweepDay(formData.getSweepDay());
                historyEntry.setToAccount(formData.getToAccount());
                historyEntry.setResultantBalance(formData.getResultantBalance());
                historyEntry.setStatus(formData.getStatus());

                formDataHistoryRepository.save(historyEntry);
                formDataRepository.deleteById(fromAccount);
            } else {
                formDataRepository.save(formData);
            }
            return ResponseEntity.ok("Data updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to update data");
        }
    }

    @GetMapping("/fetch/{fromAccount}")
    public ResponseEntity<FormData> fetchFormData(@PathVariable String fromAccount) {
        FormData formData = formDataRepository.findById(fromAccount).orElse(null);
        if (formData != null) {
            return ResponseEntity.ok(formData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
