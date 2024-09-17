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
        try {
            if ("Inactive".equals(formData.getStatus())) {
                // Save to smdh and delete from smdt
                formDataHistoryRepository.save(formData);
                formDataRepository.deleteById(formData.getFromAccount());
            } else {
                // Just save or update in smdt
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
                formDataHistoryRepository.save(formData);
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
