package com.example.sample.entity;


import com.example.sample.enums.TransactionType;

import javax.persistence.*;

/***
 * This class represents the actual entity object that is stored in the database
 */
@Entity
public class Transaction {
    /***
     * This field represents the unique identifier for the transaction
     * It is the primary key for transaction table
     */
    @Id
    private String id;

    /***
     * This field represents the name of the user who initiated the transaction
     * It is a foreign key in the db from User table.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source")
    private User source;

    /***
     * This field represents the name of the user who received the amount
     * It is a foreign key in the db from User table.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination")
    private User destination;

    /***
     * This field represents the total amount transferred
     */
    private Double amount;

    /***
     * This field represents the type of the transaction: DEBIT or CREDIT
     */
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    /***
     * This field represents the epoch milliseconds for the time at which the transaction was done.
     */
    private Long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
