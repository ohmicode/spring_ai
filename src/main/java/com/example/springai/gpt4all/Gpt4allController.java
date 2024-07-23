package com.example.springai.gpt4all;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Gpt4allController {
    Logger logger = LoggerFactory.getLogger(Gpt4allController.class);

    private final Gpt4allService gpt4allService;

    public Gpt4allController(Gpt4allService gpt4allService) {
        this.gpt4allService = gpt4allService;
    }

    @GetMapping("/gpt4all/ask")
    public String askQuestion(@RequestParam String question) {
        var start = System.currentTimeMillis();
        logger.info("Received question for gpt4all: {}", question);

        var answer = gpt4allService.askQuestion(question);

        var spent = System.currentTimeMillis() - start;
        logger.info("Answer from gpt4all (took {}ms): {}", spent, answer);
        return answer;
    }
}
