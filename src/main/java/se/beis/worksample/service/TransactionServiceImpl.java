package se.beis.worksample.service;

import org.springframework.stereotype.Service;
import se.beis.worksample.domain.Account;
import se.beis.worksample.domain.Transaction;
import se.beis.worksample.persistence.TransactionRepository;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Resource
    private TransactionRepository transactionRepository;

    @Override
    public void createTransaction(Account account, BigDecimal amount, Transaction.TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(type);
        transactionRepository.save(transaction);
        account.addTransaction(transaction);
    }
}
