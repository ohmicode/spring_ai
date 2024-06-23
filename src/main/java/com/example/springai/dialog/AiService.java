package com.example.springai.dialog;

import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.Base64;
import java.util.List;

@Service
class AiService {
    private static final String DOT = ".";
    private static final String IMAGE = "image/";

    private final OllamaChatModel aiClient;

    AiService(OllamaChatModel aiClient) {
        this.aiClient = aiClient;
    }

    String askQuestion(String question) {
        return aiClient.call(question);
    }

    String describeImage(String question, Resource image, String fileName) {
        var ext = fileName.substring(fileName.lastIndexOf(DOT) + 1);
        var userMessage = new UserMessage(question,
            List.of(new Media(MimeTypeUtils.parseMimeType(IMAGE+ext), image))
        );

        return aiClient.call(userMessage);
    }

    // Image transformation doesn't work, resulting List is always empty
    // I tried different Ollama models, but didn't find the one that can create images
    // "llava" can recognize and describe images only.
    ImageProcessingResponse processImage(String question, Resource image, String fileName) {
        var ext = fileName.substring(fileName.lastIndexOf(DOT) + 1);
        var userMessage = new UserMessage(question,
            List.of(new Media(MimeTypeUtils.parseMimeType(IMAGE+ext), image))
        );

        var options = OllamaOptions.create()
            .withModel(OllamaModel.LLAVA.id())
            .withTemperature(0.8f); // creativity level 0..1
        var answer = aiClient.call(new Prompt(List.of(userMessage), options));
        var oneResultOutput = answer.getResult().getOutput();

        Base64.Encoder encoder = Base64.getEncoder();
        var images = oneResultOutput.getMedia().stream().map(media -> {
            var data = (byte[]) media.getData();
            return encoder.encodeToString(data);
        }).toList();
        return new ImageProcessingResponse(oneResultOutput.getContent(), images);
    }
}

record ImageProcessingResponse(String answer, List<String> images) {}