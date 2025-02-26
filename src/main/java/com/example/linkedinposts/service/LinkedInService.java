package com.example.linkedinposts.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;

@Service
public class LinkedInService {
    private final WebClient webClient;

    public LinkedInService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://linkedin-data-api.p.rapidapi.com").build();
    }

    public Mono<Map> fetchPosts(String username) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/get-profile-posts")
                        .queryParam("username", username)
                        .build())
                .header("x-rapidapi-key", "71e3572bb3msh178bdf8afd28ff7p1ab947jsn6bd847596f01")
                .header("x-rapidapi-host", "linkedin-data-api.p.rapidapi.com")
                .retrieve()
                .bodyToMono(Map.class);
    }
}
