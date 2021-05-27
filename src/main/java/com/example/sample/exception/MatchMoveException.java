package com.example.sample.exception;

import com.example.sample.exception.errors.ErrorCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

/***
 * This class represents the application specific exception
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchMoveException extends Exception {
    /***
     * This field represents the ErrorCodeEnum value for this particular exception instance
     */
    private ErrorCodeEnum errorCodeEnum;

    /***
     * This field represents the list of dynamic values for the error message in error.properties
     */
    private Object[] errorDescriptionArgs;

    /***
     * This field represents the http status code for this error
     */
    private HttpStatus statusCode;

    /***
     * This field represents the error description for this particular exception
     */
    private String description;

    /***
     * This represents parameterized constructor for the instance creation
     */
    public MatchMoveException(ErrorCodeEnum errorCodeEnum, HttpStatus statusCode, String description, Object[] errorDescriptionArgs) {
        this.errorCodeEnum = errorCodeEnum;
        this.errorDescriptionArgs = errorDescriptionArgs;
        this.statusCode = statusCode;
        this.description = description;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }

    public Object[] getErrorDescriptionArgs() {
        return errorDescriptionArgs;
    }

    public void setErrorDescriptionArgs(Object[] errorDescriptionArgs) {
        this.errorDescriptionArgs = errorDescriptionArgs;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
