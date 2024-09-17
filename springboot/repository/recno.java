package com.example.demo.repository;

import com.example.demo.model.FormDataHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FormDataHistoryRepository extends JpaRepository<FormDataHistory, FormDataHistoryId> {

    @Query("SELECT COALESCE(MAX(f.recordKey), 0) FROM FormDataHistory f WHERE f.fromAccount = :fromAccount")
    Integer findMaxRecordKeyByFromAccount(@Param("fromAccount") String fromAccount);
}
