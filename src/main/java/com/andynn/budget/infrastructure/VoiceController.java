package com.andynn.budget.infrastructure;


import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/transactions")
public class VoiceController {

    private final TranscriptionModel transcriptionModel;
    private final BudgetAssistant assistant;

    public VoiceController(TranscriptionModel transcriptionModel, BudgetAssistant assistant) {
        this.transcriptionModel = transcriptionModel;
        this.assistant = assistant;
    }

    @PostMapping(value = "/voice", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public VoiceResponse voice(@RequestParam("audio")MultipartFile audio) {
        String transcription = transcriptionModel.transcribe(audio.getResource());
        String answer = assistant.chat(transcription);
        return new VoiceResponse(transcription, answer);
    }

    public record VoiceResponse(String transcription, String answer) {
    }
}
