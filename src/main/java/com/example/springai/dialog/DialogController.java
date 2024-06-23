package com.example.springai.dialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
class DialogController {
    Logger logger = LoggerFactory.getLogger(DialogController.class);

    private final AiService aiService;

    @Autowired
    DialogController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ask")
    public String askQuestion(@RequestParam String question) {
        var start = System.currentTimeMillis();
        logger.info("Received question: {}", question);

        var answer = aiService.askQuestion(question);

        var spent = System.currentTimeMillis() - start;
        logger.info("Answer from AI (took {}ms): {}", spent, answer);
        return answer;
    }

    @PostMapping("/image")
    public ImageProcessingResponse processImage(@RequestParam("file") MultipartFile file, @RequestParam String question) {
        var start = System.currentTimeMillis();
        var fileName = file.getOriginalFilename() == null ? "*.png" : file.getOriginalFilename();
        logger.info("Received file: {}, question: {}", fileName, question);

        var answer = aiService.processImage(question, file.getResource(), fileName);

        var spent = System.currentTimeMillis() - start;
        logger.info("Answer from AI (took {}ms): {}", spent, answer);
        return answer;
    }
}
