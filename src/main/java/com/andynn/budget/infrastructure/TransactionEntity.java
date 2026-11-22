package com.andynn.budget.infrastructure;


import com.andynn.budget.domain.Category;
import com.andynn.budget.domain.Transaction;
import com.andynn.budget.domain.TransactionId;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate date;

    protected  TransactionEntity() {

    }

    public static TransactionEntity fromDomain(Transaction transaction){
        TransactionEntity entity = new TransactionEntity();
        entity.id = transaction.id().value();
        entity.description = transaction.description();
        entity.amount = transaction.amount();
        entity.category = transaction.category();
        entity.date = transaction.date();
        return entity;
    }

    public Transaction toDomain() {
        return new Transaction(new TransactionId(id), description, amount, category, date);
    }


}
