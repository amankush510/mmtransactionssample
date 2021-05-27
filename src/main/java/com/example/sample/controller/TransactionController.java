package com.example.sample.controller;

import com.example.sample.constants.RequestMappings;
import com.example.sample.dto.PaginationResult;
import com.example.sample.dto.Result;
import com.example.sample.enums.TransactionType;
import com.example.sample.exception.MatchMoveException;
import com.example.sample.service.spec.TransactionService;
import com.example.sample.validators.annotations.ValidDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/***
 * This class represents controller for all the transaction operations.
 */
@RestController
@RequestMapping(RequestMappings.TRANSACTIONS_BASE_MAPPING)
public class TransactionController {
    /***
     * This field represents the Autowired instance for the TransactionService
     */
    @Autowired
    private TransactionService transactionService;

    /***
     * This method fetches the transactions between the given dates for a given user.
     *
     * @param id User id provided in the path parameter
     * @param from Start date for the transactions in format MM/dd/yyyy
     * @param to End date for the transactions in format MM/dd/yyyy
     * @param page Page number for the transaction (0-indexed) - Minimum value is 1
     * @param size Page size for the transactions - Minimum value is 1
     * @param type Transaction types - an optional filter
     *
     * @return Result object for the successful execution of the request
     *
     * @throws MatchMoveException throws MatchMoveException in the following cases:
     * 1) User does not exist
     * 2) Dates are not in a valid format (MM/dd/yyyy)
     * 3) Transactions does not exist between the given dates for the input user
     */
    @GetMapping
    public Result getTransactions(
            @PathVariable String id,
            @ValidDate @RequestParam("from") String from,
            @ValidDate @RequestParam("to") String to,
            @Min(1) @RequestParam("page") Integer page,
            @Min(1) @RequestParam("size") Integer size,
            @RequestParam(value = "type", required = false) TransactionType type) throws MatchMoveException {
        PaginationResult result = transactionService.getByPage(id, type, from, to, page, size);
        result.setSuccess(true);
        result.setMessage("Transactions fetched successfully");
        result.setStatusCode(HttpStatus.OK.value());
        return result;
    }
}
