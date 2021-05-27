package com.example.sample.entity;

import javax.persistence.*;
import java.util.List;

/***
 * This class represents the User entity class
 */
@Entity
public class User {
    /***
     * This field represents the unique identifier for the User entity
     * It is primary field in the database
     */
    @Id
    private String id;

    /***
     * This field represents the name of the user
     */
    private String name;

    /***
     * This field represents the email of the user
     */
    private String email;

    /***
     * This field represents the phone number of the user
     */
    private String phone;

    /***
     * This field represents the time in epoch millis at which the user is created
     */
    private Long createdAt;

    /***
     * This field represents the time in epoch millis at which the user is udpated
     */
    private Long updatedAt;

    /***
     * This field represents that there is a one to many relation between User and Transaction
     * userToTransaction is the table that will be created and stores the mapping
     * of transaction id and user id
     * transactionId - Unique key in the userToTransaction table
     * userId - Foreign key in the userToTransaction table
     */
    @OneToMany
    @JoinTable(name = "userToTransaction",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "transactionId"))
    private List<Transaction> transactions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
