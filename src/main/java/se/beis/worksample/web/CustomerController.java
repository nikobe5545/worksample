package se.beis.worksample.web;

import org.springframework.web.bind.annotation.*;
import se.beis.worksample.domain.Customer;
import se.beis.worksample.service.CustomerService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) throws Exception {
        return customerService.findById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
}
