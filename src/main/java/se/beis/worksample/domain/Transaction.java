package se.beis.worksample.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class Transaction extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private OffsetDateTime transactionDateTime;

    public Account getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public OffsetDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(OffsetDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public enum TransactionType {
        CREDIT, DEBIT
    }
}
