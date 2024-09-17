package com.example.demo.service;

import com.example.demo.model.FormData;
import com.example.demo.model.FormDataHistory;
import org.springframework.http.ResponseEntity;

public interface FormDataService {

    ResponseEntity<String> saveFormData(FormData formData);

    ResponseEntity<String> updateFormData(String fromAccount, FormData formData);

    ResponseEntity<FormData> fetchFormData(String fromAccount);
}
