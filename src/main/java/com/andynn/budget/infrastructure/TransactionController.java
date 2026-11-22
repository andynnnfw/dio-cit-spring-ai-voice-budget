package com.andynn.budget.infrastructure;


import com.andynn.budget.application.ListTransactionsByCategoryUseCase;
import com.andynn.budget.application.PersistTransactionInput;
import com.andynn.budget.application.PersistTransactionUseCase;
import com.andynn.budget.application.TransactionOutput;
import com.andynn.budget.domain.Category;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final PersistTransactionUseCase persistUseCase;
    private final ListTransactionsByCategoryUseCase listByCategoryUseCase;

    public TransactionController(PersistTransactionUseCase persistUseCase, ListTransactionsByCategoryUseCase listByCategoryUseCase) {
        this.persistUseCase = persistUseCase;
        this.listByCategoryUseCase = listByCategoryUseCase;
    }

    @PostMapping
    public ResponseEntity<TransactionOutput> create(@RequestBody PersistTransactionInput input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(persistUseCase.persist(input));
    }

    @GetMapping
    public List<TransactionOutput> listByCategory(@RequestParam Category category) {
        return listByCategoryUseCase.listByCategory(category);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalid(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
