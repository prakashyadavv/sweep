package com.example.demo.controller;

import com.example.demo.model.FormData;
import com.example.demo.service.FormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FormDataController {

    @Autowired
    private FormDataService formDataService;

    @PostMapping("/save")
    public ResponseEntity<String> saveFormData(@RequestBody FormData formData) {
        return formDataService.saveFormData(formData);
    }

    @PutMapping("/update/{fromAccount}")
    public ResponseEntity<String> updateFormData(@PathVariable String fromAccount, @RequestBody FormData formData) {
        return formDataService.updateFormData(fromAccount, formData);
    }

    @GetMapping("/fetch/{fromAccount}")
    public ResponseEntity<FormData> fetchFormData(@PathVariable String fromAccount) {
        return formDataService.fetchFormData(fromAccount);
    }
}
