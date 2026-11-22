package com.andynn.budget.domain;

import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    List<Transaction> findAll();

    List<Transaction> findByCategory(Category category);
}
