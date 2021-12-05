package se.beis.worksample.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.beis.worksample.domain.Account;
import se.beis.worksample.domain.Transaction;
import se.beis.worksample.persistence.TransactionRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void shouldAddCreditTransactionWhenCalledWithCreditArgument() {
        Account account = new Account();
        account.setId(0L);
        transactionService.createTransaction(account, BigDecimal.TEN, Transaction.TransactionType.CREDIT);
        assertEquals(1, account.getTransactions().size());
        Transaction transaction = account.getTransactions().iterator().next();
        assertEquals(Transaction.TransactionType.CREDIT, transaction.getType());
        assertEquals(BigDecimal.TEN, transaction.getAmount());
        assertEquals(account, transaction.getAccount());
    }

    @Test
    public void shouldAddDebitTransactionWhenCalledWithDebitArgument() {
        Account account = new Account();
        account.setId(0L);
        transactionService.createTransaction(account, BigDecimal.ONE, Transaction.TransactionType.DEBIT);
        assertEquals(1, account.getTransactions().size());
        Transaction transaction = account.getTransactions().iterator().next();
        assertEquals(Transaction.TransactionType.DEBIT, transaction.getType());
        assertEquals(BigDecimal.ONE, transaction.getAmount());
        assertEquals(account, transaction.getAccount());
    }
}