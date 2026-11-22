package com.andynn.budget.infrastructure;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class AssistantController {

    private final BudgetAssistant assistant;

    public AssistantController(BudgetAssistant assistant){
        this.assistant = assistant;
    }

    @PostMapping(value = "/ai", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String chat(@RequestBody String message){
        return assistant.chat(message);
    }
}
