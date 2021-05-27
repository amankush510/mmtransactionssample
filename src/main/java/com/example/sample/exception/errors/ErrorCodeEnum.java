package com.example.sample.exception.errors;

/***
 * This enum represents the types of errors that can occur in application
 */
public enum ErrorCodeEnum {
    //User does not exist
    USER_NOT_FOUND("404_USER_001"),
    //Transactions do not exist
    TRANSACTIONS_NOT_FOUND("404_TRANSACTION_001"),
    //Invalid transaction request like date in invalid format
    TRANSACTIONS_INVALID_REQUEST("400_TRANSACTION_002"),
    //Unexpected exception in the transactions flow
    TRANSACTIONS_INTERNAL_ERROR("500_TRANSACTION_003");

    private String errorMsg;

    ErrorCodeEnum(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return this.errorMsg;
    }
}
