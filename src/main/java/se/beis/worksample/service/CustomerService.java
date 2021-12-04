package se.beis.worksample.service;

import se.beis.worksample.domain.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findById(Long id);
}
