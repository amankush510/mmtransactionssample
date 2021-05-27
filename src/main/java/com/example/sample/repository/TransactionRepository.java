package com.example.sample.repository;

import com.example.sample.entity.Transaction;
import com.example.sample.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * This class represents the repository bean for Transaction entity
 * It is responsible for performing all the database operations for transaction entity
 * It is an extension of JpaRespository
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    /***
     * This method is responsible for fetching the transactions for a particular user between the given times
     *
     * @param user User instance
     * @param from Start time
     * @param to End time
     * @param pageable Page details
     *
     * @return List of transactions
     */
    @Query("SELECT t FROM Transaction t WHERE t.source = :user and t.timestamp between :from and :to")
    Page<Transaction> findBySource(@Param("user") User user, @Param("from") Long from, @Param("to") Long to, Pageable pageable);

    /***
     * This method is responsible for fetching the transactions for a particular user
     *
     * @param source User instance
     * @param pageable Page details
     *
     * @return List of transactions
     */
    Page<Transaction> findBySource(User source, Pageable pageable);
}
