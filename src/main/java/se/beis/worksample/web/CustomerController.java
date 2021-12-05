package se.beis.worksample.web;

import org.springframework.web.bind.annotation.*;
import se.beis.worksample.domain.Account;
import se.beis.worksample.domain.Customer;
import se.beis.worksample.service.AccountService;
import se.beis.worksample.service.CustomerService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Resource
    private CustomerService customerService;
    @Resource
    private AccountService accountService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable(required = true) Long id) throws Exception {
        return customerService.findById(id);
    }

    @GetMapping("/{id}/accounts")
    public List<Account> findAllAccountsForCustomer(@PathVariable Long id) throws Exception {
        return accountService.findAllAccountsForCustomer(id);
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }
}
