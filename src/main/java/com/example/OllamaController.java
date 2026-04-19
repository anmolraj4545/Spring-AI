package com.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stringtemplate.v4.ST;

@RestController
@RequestMapping("/api/ollama")
@CrossOrigin(origins = "*")
public class OllamaController
{

    private ChatClient chatClient;

    public OllamaController(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("{message}")
    public ResponseEntity<String> getmessage(@PathVariable String message)
    {
       ChatResponse chatResponsese=chatClient
               .prompt(message)
               .call()
               .chatResponse();


       String response=chatResponsese.getResult().getOutput().getText();

       return ResponseEntity.ok(response);


    }

}
