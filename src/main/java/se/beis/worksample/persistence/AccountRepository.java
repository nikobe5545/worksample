package se.beis.worksample.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import se.beis.worksample.domain.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByCustomerId(Long customerId);
}
