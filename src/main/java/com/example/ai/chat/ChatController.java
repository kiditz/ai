package com.example.ai.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
class ChatController {

	private final ChatClient chatClient;

	ChatController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	@GetMapping("/chats")
	String chats(@RequestParam String input) {
		return this.chatClient.prompt()
				.user(input)
				.call()
				.content();
	}
}
