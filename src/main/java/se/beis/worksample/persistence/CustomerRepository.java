package se.beis.worksample.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import se.beis.worksample.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
