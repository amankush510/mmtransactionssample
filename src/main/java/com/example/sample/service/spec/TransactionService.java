package com.example.sample.service.spec;

import com.example.sample.dto.PaginationResult;
import com.example.sample.enums.TransactionType;
import com.example.sample.exception.MatchMoveException;

/***
 * This class represents the interface for transaction operations
 */
public interface TransactionService {
    /***
     * This method fetches all the transactions for a particular user
     * It also fetches the results on the basis of page mentioned in the request
     *
     * @param userId user unique identifier
     * @param type transaction type
     * @param page page number for this request
     * @param size page size
     *
     * @return PaginationResult instance
     *
     * @throws MatchMoveException throws exception in case user does not exist and any other error
     */
    PaginationResult getAll(String userId, TransactionType type, int page, int size) throws MatchMoveException;

    /***
     * This method fetches all the transactions for a particular user between the requested dates
     * It also fetches the results on the basis of page mentioned in the request
     *
     * @param userId user unique identifier
     * @param type transaction type
     * @param from start date in format MM/dd/yyyy
     * @param to end date in format MM/dd/yyyy
     * @param page page number for this request
     * @param size page size
     *
     * @return PaginationResult instance
     *
     * @throws MatchMoveException throws exception in case user does not exist, invalid date and any other error
     */
    PaginationResult getByPage(String userId, TransactionType type, String from, String to, int page, int size) throws MatchMoveException;
}
