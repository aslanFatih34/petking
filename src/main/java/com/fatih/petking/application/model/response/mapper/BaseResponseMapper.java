package com.fatih.petking.application.model.response.mapper;

import com.fatih.petking.application.model.response.ResponseStatusType;
import com.fatih.petking.application.model.response.base.Response;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class BaseResponseMapper {

    public Response mapSuccessfulResponse() {
        Response response = new Response();
        response.setSystemTime(Clock.systemUTC().millis());
        response.setStatus(ResponseStatusType.SUCCESS.getValue());
        return response;
    }

    public Response mapFailureResponse() {
        Response response = new Response();
        response.setSystemTime(Clock.systemUTC().millis());
        response.setStatus(ResponseStatusType.FAILURE.getValue());
        return response;
    }
}
