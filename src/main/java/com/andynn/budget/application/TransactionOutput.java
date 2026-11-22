package com.andynn.budget.application;

import com.andynn.budget.domain.Category;
import com.andynn.budget.domain.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionOutput(String id, String description, BigDecimal amount, Category category, LocalDate date) {

    public static TransactionOutput from(Transaction transaction){
        return new TransactionOutput(transaction.id().value().toString(), transaction.description(), transaction.amount(), transaction.category(), transaction.date());
    }
}
