package com.andynn.budget.infrastructure;

import com.andynn.budget.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findByCategory(Category category);
}
