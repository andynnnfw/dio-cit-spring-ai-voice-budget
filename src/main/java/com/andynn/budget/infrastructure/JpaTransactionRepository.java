package com.andynn.budget.infrastructure;


import com.andynn.budget.domain.Category;
import com.andynn.budget.domain.Transaction;
import com.andynn.budget.domain.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final TransactionEntityRepository jpa;


    public JpaTransactionRepository(TransactionEntityRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return jpa.save(TransactionEntity.fromDomain(transaction)).toDomain();
    }

    @Override
    public List<Transaction> findAll() {
        return jpa.findAll().stream().map(TransactionEntity::toDomain).toList();
    }

    @Override
    public List<Transaction> findByCategory(Category category) {
        return jpa.findByCategory(category).stream().map(TransactionEntity::toDomain).toList();
    }

}
