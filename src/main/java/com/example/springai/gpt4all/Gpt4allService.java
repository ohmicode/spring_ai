package com.example.springai.gpt4all;

import com.hexadevlabs.gpt4all.LLModel;
import org.springframework.stereotype.Service;

@Service
public class Gpt4allService {

    private final LLModel gpt4allModel;
    private final LLModel.GenerationConfig gpt4allGenerationConfig;

    public Gpt4allService(LLModel gpt4allModel, LLModel.GenerationConfig gpt4allGenerationConfig) {
        this.gpt4allModel = gpt4allModel;
        this.gpt4allGenerationConfig = gpt4allGenerationConfig;
    }

    public String askQuestion(String prompt) {
        return gpt4allModel.generate(prompt, gpt4allGenerationConfig);
    }
}
