package se.beis.worksample.service;

import se.beis.worksample.domain.Account;
import se.beis.worksample.domain.Transaction;

import java.math.BigDecimal;

public interface TransactionService {

    void createTransaction(Account account, BigDecimal amount, Transaction.TransactionType type);
}
