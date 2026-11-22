package com.andynn.budget.domain;


import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(TransactionId id, String description, BigDecimal amount, Category category, LocalDate date) {

   public Transaction {
       if (description == null || description.isBlank()){
           throw new IllegalArgumentException("Descricao eh obrigatoria!");
       }

       if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
           throw new IllegalArgumentException("Valor deve ser maior que zero!");
       }

       if (category == null) {
           throw new IllegalArgumentException("A categoria eh obrigatoria!");
       }

       if (date == null) {
           throw new IllegalArgumentException("A data eh obrigatoria!");
       }
   }

   public static Transaction create(String description, BigDecimal amount, Category category, LocalDate date){
       return new Transaction(TransactionId.generate(), description, amount, category, date);
   }
}
