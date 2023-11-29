package de.neuefische.ffmjava232chatgpt;

import java.util.List;

public record ChatGPTRequest(
        String model,
        List<ChatGPTMessage> messages
) {
}
