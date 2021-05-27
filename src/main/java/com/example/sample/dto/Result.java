package com.example.sample.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/***
 * This class represents the generalised response format
 * that will be returned to the client
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    /***
     * This field represents the status of request execution.
     * False - if the request failed
     * True - if the request execution is successful
     */
    private boolean success;

    /***
     * This field represents the http status code for the request
     */
    private int statusCode;

    /***
     * This field represents the message for successful requests
     */
    private String message;

    /***
     * This field represents the error code from the ErrorCodeEnum.
     * It can be used on client side to show a toast with appropriate user-friendly error message.
     */
    private String errorCode;

    /***
     * This field represents the error message corresponding to the
     * error code. Error messages are defined in error.properties
     */
    private String error;

    /***
     * This field represents the resources returned by the request.
     * For e.g transactions data will be stored in this field while returning the response
     */
    private Map<String, Object> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
