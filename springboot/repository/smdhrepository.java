package com.example.demo.repository;

import com.example.demo.model.FormDataHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormDataHistoryRepository extends JpaRepository<FormDataHistory, String> {
}
