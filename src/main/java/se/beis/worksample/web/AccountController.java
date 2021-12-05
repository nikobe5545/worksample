package se.beis.worksample.web;

import org.springframework.web.bind.annotation.*;
import se.beis.worksample.domain.Account;
import se.beis.worksample.service.AccountService;
import se.beis.worksample.web.dto.NewAccountRequest;

import javax.annotation.Resource;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable Long id) throws Exception {
        return accountService.findById(id);
    }

    @PostMapping("/customers/{customerId}/accounts")
    public Account createNewAccount(@RequestBody NewAccountRequest newAccountRequest) throws Exception {
        validateRequest(newAccountRequest);
        return accountService.addAccount(newAccountRequest.getCustomerId(), newAccountRequest.getInitialCredit());
    }

    private void validateRequest(NewAccountRequest newAccountRequest) {
        if (newAccountRequest.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer id must not be null");
        }
    }
}
