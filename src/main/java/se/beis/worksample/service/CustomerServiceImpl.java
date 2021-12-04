package se.beis.worksample.service;

import org.springframework.stereotype.Service;
import se.beis.worksample.domain.Customer;
import se.beis.worksample.persistence.CustomerRepository;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerRepository customerRepository;

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
}
