package com.afvs.challenge.models.dtos;

public class GenericResponseMessageDto {
    private String message;

    public GenericResponseMessageDto() {
    }

    public GenericResponseMessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
