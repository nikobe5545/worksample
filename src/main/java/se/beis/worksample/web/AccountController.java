package se.beis.worksample.web;

import org.springframework.web.bind.annotation.*;
import se.beis.worksample.domain.Account;
import se.beis.worksample.service.AccountService;
import se.beis.worksample.web.dto.NewAccountRequest;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable Long id) throws Exception {
        return accountService.findById(id);
    }

    @GetMapping("/customers/{customerId}/accounts")
    public List<Account> findAllAccountsForCustomer(@PathVariable Long customerId) {
        return accountService.findAllAccountsForCustomer(customerId);
    }

    @PostMapping("/customers/{customerId}/accounts")
    public Account createNewAccount(@PathVariable Long customerId, @RequestBody NewAccountRequest newAccountRequest) throws Exception {
        return accountService.addAccount(customerId, newAccountRequest.getInitialCredit());
    }
}
