package com.worknest.ai.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OllamaService {
	
	private final OllamaChatModel chatModel;

    public OllamaService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String queryModel(String prompt) {
        return chatModel.call(prompt); // directly ask the model
    }
}
