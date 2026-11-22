package com.andynn.budget.application;

import com.andynn.budget.domain.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PersistTransactionInput(String description, BigDecimal amount, Category category, LocalDate date) {
}
