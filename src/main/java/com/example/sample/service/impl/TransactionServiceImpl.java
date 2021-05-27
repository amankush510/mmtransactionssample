package com.example.sample.service.impl;

import com.example.sample.dto.PaginationResult;
import com.example.sample.dto.TransactionDto;
import com.example.sample.entity.Transaction;
import com.example.sample.entity.User;
import com.example.sample.enums.TransactionType;
import com.example.sample.exception.errors.ErrorCodeEnum;
import com.example.sample.exception.MatchMoveException;
import com.example.sample.repository.TransactionRepository;
import com.example.sample.repository.UserRepository;
import com.example.sample.service.spec.TransactionService;
import com.example.sample.transformers.TransactionTransformer;
import com.example.sample.utils.DateUtil;
import com.example.sample.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/***
 * This class represents the business logic layer(Service) for transaction entity
 * It is an implementation of TransactionService specification
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    /***
     * This field represents the autowired bean for TransactionRepository instance
     */
    @Autowired
    private TransactionRepository transactionRepository;

    /***
     * This field represents the autowired bean for UserRepository instance
     */
    @Autowired
    private UserRepository userRepository;

    /***
     * This field represents the autowired bean for TransactionTransformer instance
     */
    @Autowired
    private TransactionTransformer transactionTransformer;

    @Override
    public PaginationResult getAll(String userId, TransactionType type, int page, int size) throws MatchMoveException {
        //Validating if user exists for given user id
        User user = validateUser(userId);

        try {
            //Creating pageable instance for page and size and also for sorting on the basis of timestamp
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("timestamp")));

            //Fetching the page data from database
            Page<Transaction> pageData = transactionRepository.findBySource(user, pageable);

            //Throw an error if no transactions found for this page and user
            if (CollectionUtils.isEmpty(pageData.getContent())) {
                throw new MatchMoveException(ErrorCodeEnum.TRANSACTIONS_NOT_FOUND,
                        HttpStatus.NOT_FOUND, "No transactions found between the provided dates", null);
            }

            //Returning success response for the fetched transactions after filtering on the basis of transaction type
            return ResponseUtil.createPaginationResult(filterTransformTransactions(pageData, type), "transactions",
                    pageData.getTotalElements(), pageData.getNumber(), pageData.getTotalPages());
        }  catch (MatchMoveException e) {
            throw e;
        } catch (Exception e) {
            //Throw an error in case if some unexpected exception occured like database errors etc
            throw new MatchMoveException(ErrorCodeEnum.TRANSACTIONS_INTERNAL_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process request", null);
        }
    }

    @Override
    public PaginationResult getByPage(String userId, TransactionType type, String from, String to, int page, int size) throws MatchMoveException {
        User user = validateUser(userId);
        try {
            //Creating pageable instance for page and size and also for sorting on the basis of timestamp
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("timestamp")));

            //Converting dates in format MM/dd/yyyy to epoch millisecond
            Long fromDate = DateUtil.getStartOfDayTime(from);
            Long toDate = DateUtil.getEndOfDayTime(to);

            //Fetching the page data from database for this user and between the given dates
            Page<Transaction> pageData = transactionRepository.findBySource(user, fromDate, toDate, pageable);

            //Throw an error if no transactions found for this page and user
            if(CollectionUtils.isEmpty(pageData.getContent())) {
                throw new MatchMoveException(ErrorCodeEnum.TRANSACTIONS_NOT_FOUND,
                        HttpStatus.NOT_FOUND, "No transactions found between the provided dates", null);
            }

            //Returning success response for the fetched transactions after filtering on the basis of transaction type
            return ResponseUtil.createPaginationResult(filterTransformTransactions(pageData, type), "transactions",
                    pageData.getTotalElements(), pageData.getNumber(), pageData.getTotalPages());
        } catch (DateTimeParseException e) {
            //Throw an error in case dates are provided in invalid format
            throw new MatchMoveException(ErrorCodeEnum.TRANSACTIONS_INVALID_REQUEST,
                    HttpStatus.BAD_REQUEST, "Date provided in invalid format", new String[]{from + "," + to, "date"});
        } catch (MatchMoveException e) {
            throw e;
        } catch (Exception e) {
            //Throw an error in case if some unexpected exception occured like database errors etc
            throw new MatchMoveException(ErrorCodeEnum.TRANSACTIONS_INTERNAL_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process request", null);
        }
    }

    /***
     * This method is used to filter the transactions on the basis of transaction type
     *
     * @param pageData Page<Transaction> instance returned by the repository
     * @param transactionType transaction type - default null
     *
     * @return List<Transaction> - filtered list of the transactions
     */
    private List<TransactionDto> filterTransformTransactions(Page<Transaction> pageData, TransactionType transactionType) {
        List<TransactionDto> transactions = transactionTransformer.toDtoList(pageData.getContent());
        if(transactionType != null) {
            transactions = transactions.stream().filter(transactionDto -> transactionDto.getType().equals(transactionType))
                    .collect(Collectors.toList());
        }
        return transactions;
    }

    /***
     * This method is used to fetch the user for given user id
     *
     * @param userId Unique identifier for the user
     *
     * @return User instance corresponding to user id
     * @throws MatchMoveException throws an error if user does not exist for the provided user id
     */
    private User validateUser(String userId) throws MatchMoveException {
        //Fetching the user for the given userId from db
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            //Throwing an error if user is not found for the given user id
            throw new MatchMoveException(ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND, "User not found for id: " + userId, new String[]{userId});
        }
        return user.get();
    }
}
