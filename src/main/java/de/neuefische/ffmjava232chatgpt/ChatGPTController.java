package de.neuefische.ffmjava232chatgpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/chatgpt")
public class ChatGPTController {
    @Value("${app.openai-api-key}")
    private String chatGPTApiKey;

    @PostMapping
    public String post(@RequestBody String prompt) {
        ChatGPTResponse response = Objects.requireNonNull(
                        WebClient.create()
                                .post()
                                .uri("https://api.openai.com/v1/chat/completions")
                                .header("Authorization", "Bearer " + chatGPTApiKey)
                                .bodyValue(
                                        new ChatGPTRequest(
                                                "gpt-3.5-turbo",
                                                List.of(new ChatGPTMessage(
                                                        "user",
                                                        prompt
                                                ))
                                        )
                                )
                                .retrieve()
                                .toEntity(ChatGPTResponse.class)
                                .block())
                .getBody();

        if (response.choices().size() > 0) {
            return response.choices().get(0).message().content();
        } else {
            return "";
        }
    }
}
