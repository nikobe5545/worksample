package se.beis.worksample.service;

import se.beis.worksample.domain.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<Account> findAllAccountsForCustomer(Long customerId);

    Account findById(Long accountId) throws Exception;

    Account addAccount(Long customerId, BigDecimal initialCredit) throws Exception;

    void credit(Account account, BigDecimal amount);

    void debit(Account account, BigDecimal amount);
}
