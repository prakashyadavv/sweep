package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smdh")
public class FormDataHistory {

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


*****REC_NO***********8

package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "smdh")
@IdClass(FormDataHistoryId.class) // Use composite key class
public class FormDataHistory {

    @Id
    private String fromAccount; // Composite primary key part

    @Id
    private Integer recordKey; // Composite primary key part

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

    public Integer getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(Integer recordKey) {
        this.recordKey = recordKey;
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
        th
