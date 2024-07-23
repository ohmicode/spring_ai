package com.example.springai.gpt4all;

import com.hexadevlabs.gpt4all.LLModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class Gpt4allConfig {
    private final Gpt4allProperties gpt4allProperties;

    public Gpt4allConfig(Gpt4allProperties gpt4allProperties) {
        this.gpt4allProperties = gpt4allProperties;
    }

    @Bean
    public LLModel gpt4allModel() {
        return new LLModel(Path.of(gpt4allProperties.getModelFilePath()));
    }

    @Bean
    public LLModel.GenerationConfig gpt4allGenerationConfig() {
        return LLModel.config().withNPredict(4096).build();
    }
}
