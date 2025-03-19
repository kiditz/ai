package com.example.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAroundAdvisorChain;
import org.springframework.ai.chat.model.MessageAggregator;

import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import reactor.core.publisher.Flux;

@Slf4j
public class LoggingAdvisor implements CallAroundAdvisor, StreamAroundAdvisor {

	@Override
	public String getName() {
		return "LoggingAdvisor";
	}

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public AdvisedResponse aroundCall(@NonNull AdvisedRequest advisedRequest, @NonNull CallAroundAdvisorChain chain) {
//		log.info("\nRequest: {}", advisedRequest);
		AdvisedResponse response = chain.nextAroundCall(advisedRequest);
//		log.info("\nResponse: {}", response);
		return response;

	}

	@Override
	public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {
		log.info("\nRequest: {}", advisedRequest);
		Flux<AdvisedResponse> responses = chain.nextAroundStream(advisedRequest);
		return new MessageAggregator().aggregateAdvisedResponse(responses, aggregatedAdvisedResponse -> {
			log.info("\nResponse: {}", aggregatedAdvisedResponse);
		});
	}

}