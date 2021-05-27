package com.example.sample.dto;

import com.example.sample.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/***
 * This class represents the transaction data transfer object.
 * This will be used instead of the entity class during communication with the client
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {
    /***
     * This field represents the unique identifier for the transaction
     */
    private String id;

    /***
     * This field represents the name of the user who initiated the transaction
     */
    private String from;

    /***
     * This field represents the name of the user who received the amount
     */
    private String to;

    /***
     * This field represents the total amount transferred
     */
    private Double amount;

    /***
     * This field represents the type of the transaction: DEBIT or CREDIT
     */
    private TransactionType type;

    /***
     * This field represents the epoch milliseconds for the time at which the transaction was done
     */
    private String time;
}
