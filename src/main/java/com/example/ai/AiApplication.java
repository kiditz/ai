package com.example.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.util.Objects;

@SpringBootApplication
@Slf4j
public class AiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiApplication.class, args);
	}
	@Bean
	public ChatMemory chatMemory() {
		return new InMemoryChatMemory();
	}
	/*@Bean
	CommandLineRunner ingestTermOfServiceToVectorStore(
			EmbeddingModel embeddingModel, VectorStore vectorStore,
			@Value("classpath:rag/terms-of-service.txt") Resource termsOfServiceDocs) {

		return args -> {
			// Ingest the document into the vector store
			vectorStore.write(
					new TokenTextSplitter().transform(
							new TextReader(termsOfServiceDocs).read()));

			Objects.requireNonNull(vectorStore.similaritySearch("Cancelling Bookings")).forEach(doc -> {
				log.info("Similar Document: {}", doc.getText());
			});
		};
	}*/

}
