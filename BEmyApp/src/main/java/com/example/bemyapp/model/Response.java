package com.example.bemyapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    private String message;
    private String status;
    private TbUser tbUser;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("data")
    public TbUser getTbUser() {
        return tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Response() {
    }

    public Response(String message, String status, TbUser tbUser) {
        this.message = message;
        this.status = status;
        this.tbUser = tbUser;
    }
}
