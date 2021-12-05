package se.beis.worksample.web;

import org.springframework.web.bind.annotation.*;
import se.beis.worksample.domain.Customer;
import se.beis.worksample.exception.ResourceNotFoundException;
import se.beis.worksample.service.CustomerService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable(required = true) Long id) throws Exception {
        return customerService.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PutMapping("/customer")
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }
}
