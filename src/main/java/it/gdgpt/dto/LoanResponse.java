package it.gdgpt.dto;

public class LoanResponse {

    private Status status;
    private String notes;

    public LoanResponse(Status status, String notes) {
        this.status = status;
        this.notes = notes;
    }

    public enum Status{
        SUCCESS,
        FAILED
    }

    public Status getStatus() {
        return status;
    }

    public LoanResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public LoanResponse setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}
