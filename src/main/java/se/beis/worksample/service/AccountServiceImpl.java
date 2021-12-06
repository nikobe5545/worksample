package se.beis.worksample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.beis.worksample.domain.Account;
import se.beis.worksample.domain.Customer;
import se.beis.worksample.domain.Transaction;
import se.beis.worksample.exception.ResourceNotFoundException;
import se.beis.worksample.persistence.AccountRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private CustomerService customerService;
    @Resource
    private AccountRepository accountRepository;
    @Resource
    private TransactionService transactionService;

    @Override
    public List<Account> findAllAccountsForCustomer(Long customerId) {
        return accountRepository.findAllByCustomerId(customerId);
    }

    @Override
    public Account findById(Long accountId) throws Exception {
        return accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Account with id %d not found", accountId)));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public Account addAccount(Long customerId, BigDecimal initialCredit) throws Exception {
        log.debug("Creating new account for customer {} with initial credit of {}", customerId, initialCredit);
        Customer customer = customerService.findById(customerId);
        Account account = createAndPersistNewAccountFor(customer);
        if (initialCredit.compareTo(BigDecimal.ZERO) > 0) {
            log.debug("Initial credit is above 0. A credit transaction for the amount of {} will be created.", initialCredit);
            credit(account, initialCredit);
        }
        log.debug("Successfully created new account for customer {} with initial credit of {}", customerId, initialCredit);
        return account;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public void credit(Account account, BigDecimal amount) {
        log.debug("Crediting {} to account with id {}", amount, account.getId());
        transactionService.createTransaction(account, amount, Transaction.TransactionType.CREDIT);
        creditAccountBalance(account, amount);
        accountRepository.save(account);
    }

    void creditAccountBalance(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public void debit(Account account, BigDecimal amount) {
        log.debug("Debiting {} from account with id {}", amount, account.getId());
        transactionService.createTransaction(account, amount, Transaction.TransactionType.DEBIT);
        debitAccountBalance(account, amount);
        accountRepository.save(account);
    }

    void debitAccountBalance(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().subtract(amount));
    }

    private Account createAndPersistNewAccountFor(Customer customer) {
        Account account = new Account();
        account.setCustomer(customer);
        account.setBalance(BigDecimal.ZERO);
        accountRepository.save(account);
        return account;
    }

}
