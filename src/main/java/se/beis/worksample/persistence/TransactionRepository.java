package se.beis.worksample.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import se.beis.worksample.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
