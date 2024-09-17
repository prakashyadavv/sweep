package com.example.demo.controller;

import com.example.demo.model.FormData;
import com.example.demo.repository.FormDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FormDataController {

    @Autowired
    private FormDataRepository formDataRepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveFormData(@RequestBody FormData formData) {
        try {
            // Save or update FormData
            formDataRepository.save(formData);
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to save data");
        }
    }

    @PutMapping("/update/{fromAccount}")
    public ResponseEntity<String> updateFormData(@PathVariable String fromAccount, @RequestBody FormData formData) {
        if (!formDataRepository.existsById(fromAccount)) {
            return ResponseEntity.status(404).body("Record not found");
        }

        try {
            // Update existing record
            formDataRepository.save(formData);
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

    // Additional endpoints as needed
}
