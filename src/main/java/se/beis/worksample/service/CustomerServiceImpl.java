package se.beis.worksample.service;

import org.springframework.stereotype.Service;
import se.beis.worksample.domain.Customer;
import se.beis.worksample.exception.ResourceNotFoundException;
import se.beis.worksample.persistence.CustomerRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Customer with id %d not found", id)));
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

}
