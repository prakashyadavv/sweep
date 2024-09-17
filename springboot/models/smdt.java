package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "your_table_name")
public class FormData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String fromAccount;
    private String sweepDay;
    private String toAccount;
    private String resultantBalance;
    private String status;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getSweepDay() {
        return sweepDay;
    }

    public void setSweepDay(String sweepDay) {
        this.sweepDay = sweepDay;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getResultantBalance() {
        return resultantBalance;
    }

    public void setResultantBalance(String resultantBalance) {
        this.resultantBalance = resultantBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


*****************
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "form_data")
public class FormData {

    @Id
    private String fromAccount; // Primary key

    private String action;
    private String sweepDay;
    private String toAccount;
    private String resultantBalance;
    private String status;

    // Getters and Setters

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSweepDay() {
        return sweepDay;
    }

    public void setSweepDay(String sweepDay) {
        this.sweepDay = sweepDay;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getResultantBalance() {
        return resultantBalance;
    }

    public void setResultantBalance(String resultantBalance) {
        this.resultantBalance = resultantBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
