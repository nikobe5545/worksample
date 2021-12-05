package se.beis.worksample.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.beis.worksample.domain.Account;
import se.beis.worksample.domain.Customer;
import se.beis.worksample.domain.Transaction;
import se.beis.worksample.exception.ResourceNotFoundException;
import se.beis.worksample.persistence.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private TransactionService transactionService;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void shouldReturnAccountWhenAccountExists() throws Exception {
        Account account = new Account();
        account.setId(0L);
        when(accountRepository.findById(0L)).thenReturn(Optional.of(account));
        Account returnedAccount = accountService.findById(0L);
        assertEquals(account, returnedAccount);
    }

    @Test
    public void shouldThrowExceptionWhenAccountDoesNotExist() {
        when(accountRepository.findById(0L)).thenReturn(Optional.empty());
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
                () -> accountService.findById(0L), "Should have thrown a ResourceNotFoundException");
        assertEquals("Account with id 0 not found", thrown.getMessage());
    }

    @Test
    public void shouldAddAccountWithoutInitialCreditWhenInitialCreditIsZero() throws Exception {
        Customer customer = new Customer();
        customer.setId(0L);
        when(customerService.findById(0L)).thenReturn(customer);
        accountService.addAccount(0L, BigDecimal.ZERO);
        verify(accountRepository, times(1)).save(isA(Account.class));
        verify(transactionService, never()).createTransaction(any(), any(), any());
    }

    @Test
    public void shouldAddAccountWithInitialCreditWhenInitialCreditIsAboveZero() throws Exception {
        Customer customer = new Customer();
        customer.setId(0L);
        when(customerService.findById(0L)).thenReturn(customer);
        accountService.addAccount(0L, BigDecimal.TEN);
        verify(accountRepository, times(2)).save(isA(Account.class));
        verify(transactionService, times(1))
                .createTransaction(isA(Account.class), eq(BigDecimal.TEN), eq(Transaction.TransactionType.CREDIT));
    }

    @Test
    @Disabled
    public void shouldDoWhatWhenInitialCreditIsBelowZero() {
        // Not specified in the requirements what should happen in this case.
    }

    @Test
    public void shouldNotAddAccountWhenCustomerDoesNotExist() throws Exception {
        when(customerService.findById(0L)).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class,
                () -> accountService.addAccount(0L, BigDecimal.TEN),
                "Should have thrown a ResourceNotFoundException");
        verify(accountRepository, never()).save(isA(Account.class));
        verify(transactionService, never()).createTransaction(any(), any(), any());
    }

    @Test
    public void shouldCreateACreditTransactionWhenCallingCredit() {
        Account account = new Account();
        account.setId(0L);
        account.setBalance(BigDecimal.TEN);
        accountService.credit(account, BigDecimal.ONE);
        verify(transactionService, times(1))
                .createTransaction(account, BigDecimal.ONE, Transaction.TransactionType.CREDIT);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void shouldCreateADebitTransactionWhenCallingDebit() {
        Account account = new Account();
        account.setId(0L);
        account.setBalance(BigDecimal.TEN);
        accountService.debit(account, BigDecimal.ONE);
        verify(transactionService, times(1))
                .createTransaction(account, BigDecimal.ONE, Transaction.TransactionType.DEBIT);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void shouldUpdateAccountBalanceWhenCallingCredit() {
        Account account = new Account();
        account.setId(0L);
        account.setBalance(BigDecimal.TEN);
        accountService.creditAccountBalance(account, new BigDecimal("5.11"));
        assertEquals(new BigDecimal("15.11"), account.getBalance());
    }

    @Test
    public void shouldUpdateAccountBalanceWhenCallingDebit() {
        Account account = new Account();
        account.setId(0L);
        account.setBalance(BigDecimal.TEN);
        accountService.debitAccountBalance(account, new BigDecimal("1.51"));
        assertEquals(new BigDecimal("8.49"), account.getBalance());
    }
}