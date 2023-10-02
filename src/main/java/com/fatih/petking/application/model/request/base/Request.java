package com.fatih.petking.application.model.request.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Request {
    private String locale;
    private String conversationId;
}
