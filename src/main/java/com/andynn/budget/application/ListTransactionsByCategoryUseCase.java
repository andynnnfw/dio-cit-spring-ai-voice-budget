package com.andynn.budget.application;


import com.andynn.budget.domain.Category;
import com.andynn.budget.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTransactionsByCategoryUseCase {

    private final TransactionRepository repository;

    public ListTransactionsByCategoryUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    @Tool(description = "Lista os gastos do usuario de uma categoria. Use quando o usuario perguntar quanto ou com o que gastou em uma categoria.")
    public List<TransactionOutput> listByCategory(
            @ToolParam(description = "Categoria dos gastos a consultar") Category category) {
        return repository.findByCategory(category).stream().map(TransactionOutput::from).toList();
    }
}
