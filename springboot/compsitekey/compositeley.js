package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

public class FormDataHistoryId implements Serializable {

    private String fromAccount;
    private Integer recordKey;

    public FormDataHistoryId() {}

    public FormDataHistoryId(String fromAccount, Integer recordKey) {
        this.fromAccount = fromAccount;
        this.recordKey = recordKey;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormDataHistoryId that = (FormDataHistoryId) o;
        return Objects.equals(fromAccount, that.fromAccount) &&
               Objects.equals(recordKey, that.recordKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromAccount, recordKey);
    }
}
