package com.example.sample.exception.handler;

import com.example.sample.dto.Result;
import com.example.sample.exception.MatchMoveException;
import com.example.sample.utils.ErrorCodeUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/***
 * This class represents the ControllerAdvice which handles all the uncaught errors in the application
 */
@ControllerAdvice
public class MatchMoveExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method handles all the uncaught MatchMoveException for the application
     * It creates a Result object and pass it to handleExceptionInternal to create ResponseEntity
     *
     * @param ex MatchMoveException instance
     * @param request WebRequest instance
     *
     * @return ResponseEntity instance
     */
    @ExceptionHandler(value = { MatchMoveException.class})
    protected ResponseEntity<Object> handleConflict(MatchMoveException ex, WebRequest request) {
        Result result = new Result();
        result.setSuccess(false);
        result.setStatusCode(ex.getStatusCode().value());
        result.setErrorCode(ex.getErrorCodeEnum().getErrorCode());
        result.setError(ErrorCodeUtil.INSTANCE.getErrorDescription(ex.getErrorCodeEnum(), ex.getErrorDescriptionArgs()));
        result.setMessage(ex.getDescription());
        return handleExceptionInternal(ex, result, new HttpHeaders(), ex.getStatusCode(), request);
    }
}
