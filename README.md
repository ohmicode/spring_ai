# Spring AI with Ollama Chat

Playing around with Spring AI using Ollama Chat. Including image processing.
Idea is to use local AI models embedded into Spring Boot application.

### Prerequisites

Ollama server installed and running. Configured by application property
```properties
spring.ai.ollama.base-url=http://localhost:11434
```
AI model used for image processing: llava

### Usage examples

```bash
curl 'http://localhost:8080/ask?question=tell%20me%20a%20joke'
curl -X POST -F "file=@image.png" 'http://localhost:8080/image?question=what%20do%20you%20see%20here'
curl -X POST -F "file=@portrait.jpg" 'http://localhost:8080/image?question=append%20a%20hat%20here'
curl -X POST -F "file=@portrait.jpg" 'http://localhost:8080/image?question=make%20it%20looks%20like%20a%20cyborg'
```


### Reference Documentation

For further reference, please consider the following sections:
* [Ollama](https://ollama.com/)
* [Spring AI - Ollama](https://docs.spring.io/spring-ai/reference/api/chat/ollama-chat.html)
