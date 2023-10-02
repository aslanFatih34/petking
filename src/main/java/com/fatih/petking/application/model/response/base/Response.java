package com.fatih.petking.application.model.response.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String status;
    private String errorCode;
    private String errorMessage;
    private String locale;
    private long systemTime;
    private String conversationId;
}
