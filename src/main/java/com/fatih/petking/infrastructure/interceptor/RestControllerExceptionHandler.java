package com.fatih.petking.infrastructure.interceptor;

import com.fatih.petking.application.model.response.ResponseStatusType;
import com.fatih.petking.application.model.response.base.Response;
import com.fatih.petking.domain.exception.base.BusinessException;
import com.fatih.petking.infrastructure.locale.MessageSourceLocalize;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestControllerExceptionHandler {
    private static final String ERROR_MESSAGE_SPLITTER = ";";
    private static final String SYSTEM_ERROR = "common.error.system.occurred";

    private final MessageSourceLocalize messageSourceLocalize;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response> handleBusinessException(BusinessException ex) {
        log.error("Validation exception occurred", ex);
        var message = messageSourceLocalize.getLocaleMessage(ex.getKey(), ex.getArguments());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(createErrorResponse(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception ex) {
        log.error("System exception occurred", ex);
        var message = messageSourceLocalize.getLocaleMessage(SYSTEM_ERROR);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(createErrorResponse(message));
    }

    private Response createErrorResponse(String message) {
        var errorCodeMessage = message.split(ERROR_MESSAGE_SPLITTER);
        return createResponseWithErrorCode(errorCodeMessage[0], errorCodeMessage[1]);
    }

    private Response createResponseWithErrorCode(String errorCode, String errorMessage) {
        var response = new Response();
        response.setStatus(ResponseStatusType.FAILURE.getValue());
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        response.setSystemTime(Clock.systemUTC().millis());
        response.setLocale(getLocale());
        return response;
    }

    private static String getLocale() {
        return LocaleContextHolder.getLocale().toLanguageTag();
    }
}
