package de.neuefische.ffmjava232chatgpt;

import java.util.List;

public record ChatGPTResponse(
        List<ChatGPTResponseChoice> choices
) {
}
