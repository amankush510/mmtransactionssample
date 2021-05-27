package com.example.sample.transformers;

import com.example.sample.dto.TransactionDto;
import com.example.sample.entity.Transaction;
import com.example.sample.utils.DateUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/***
 * This class is used to perform transformations from transaction dto to transaction entity and vice versa.
 */
@Component
public class TransactionTransformer {

    /***
     * This method converts transaction entity object to transaction dto
     * @param transaction Transaction entity object
     * @return TransactionDto instance
     */
    public TransactionDto toDto(Transaction transaction) {
        if(transaction == null) {
            return null;
        }
        return TransactionDto.builder()
                .from(transaction.getSource().getName())
                .to(transaction.getDestination().getName())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .time(DateUtil.getDate(transaction.getTimestamp()))
                .build();
    }

    /***
     * This method converts list of transaction entity object to list of transaction dto
     * @param transactions List of Transaction instances
     * @return TransactionDto instance
     */
    public List<TransactionDto> toDtoList(List<Transaction> transactions) {
        if(CollectionUtils.isEmpty(transactions)) {
            return null;
        }
        List<TransactionDto> transactionList = new ArrayList<>();
        transactions.forEach(transaction -> transactionList.add(toDto(transaction)));
        return transactionList;
    }
}
