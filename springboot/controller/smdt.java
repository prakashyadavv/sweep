package com.example.demo.controller;

import com.example.demo.model.FormData;
import com.example.demo.repository.FormDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Adjust according to your React app's origin
public class FormDataController {

    @Autowired
    private FormDataRepository formDataRepository;

    @PostMapping("/save")
    public void saveFormData(@RequestBody FormData formData) {
        formDataRepository.save(formData);
    }
}


**************

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

    // Additional endpoints as needed
}
