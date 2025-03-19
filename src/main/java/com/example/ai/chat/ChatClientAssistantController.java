package com.example.ai.chat;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chats")
@Tag(name = "Chats")
class ChatClientAssistantController {
	private final ChatClientAssistant assistant;

	public ChatClientAssistantController(ChatClientAssistant assistant) {
		this.assistant = assistant;
	}

	@GetMapping
	Flux<String> chat(@RequestParam String id, @RequestParam String chat) {
		return assistant.chat(id, chat);
	}
}
