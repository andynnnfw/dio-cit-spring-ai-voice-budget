package com.andynn.budget.application;

import com.andynn.budget.domain.Transaction;
import com.andynn.budget.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class PersistTransactionUseCase {

    private final TransactionRepository repository;

    public PersistTransactionUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    @Tool(description = "Registra um novo gasto do usuario. Use quando o usuario disser que gastou ou pagou algo.")
    public TransactionOutput persist(
            @ToolParam(description = "Dados do gasto: descriçao, valor em reais, categoria e data no formato yyyy-MM-dd") PersistTransactionInput input) {
        Transaction transaction = Transaction.create(input.description(), input.amount(), input.category(), input.date());
        Transaction saved = repository.save(transaction);
        return TransactionOutput.from(saved);
    }
}
