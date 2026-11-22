package com.andynn.budget.infrastructure;

import com.andynn.budget.application.ListTransactionsByCategoryUseCase;
import com.andynn.budget.application.PersistTransactionUseCase;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.core.io.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class BudgetAssistant {
    private final ChatClient chatClient;
    private final Resource systemPrompt;

    public BudgetAssistant(ChatClient.Builder builder,
                           PersistTransactionUseCase persistUseCase,
                           ListTransactionsByCategoryUseCase listByCategoryUseCase,
                           @Value("classpath:prompts/system-message.st") Resource systemPrompt) {
        this.chatClient = builder
                .defaultOptions(OpenAiChatOptions.builder().extraBody(Map.of("include_reasoning", false)))
                .defaultTools(persistUseCase, listByCategoryUseCase)
                .build();
        this.systemPrompt = systemPrompt;
    }

    public String chat(String message) {
        return chatClient.prompt().system(s -> s.text(systemPrompt).param("today", LocalDate.now()))
                .user(message)
                .call()
                .content();
    }
}
