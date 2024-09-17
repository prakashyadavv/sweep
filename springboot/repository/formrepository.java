package com.example.demo.repository;

import com.example.demo.model.FormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDataRepository extends JpaRepository<FormData, Long> {
}


************

package com.example.demo.repository;

import com.example.demo.model.FormData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormDataRepository extends JpaRepository<FormData, String> {
    // No additional methods needed unless you want custom queries
}
