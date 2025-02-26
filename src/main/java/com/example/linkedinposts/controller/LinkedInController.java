package com.example.linkedinposts.controller;

import com.example.linkedinposts.service.LinkedInService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class LinkedInController {
    private final LinkedInService linkedInService;

    public LinkedInController(LinkedInService linkedInService) {
        this.linkedInService = linkedInService;
    }
    
    @GetMapping("/")
    public String homePage() {
    	return "index";
    }

    @GetMapping("/posts")
    public String fetchPosts(@RequestParam String username, Model model) {
        Mono<Map> postsData = linkedInService.fetchPosts(username);
        model.addAttribute("posts", postsData.block().get("data"));
        model.addAttribute("username", username);
        return "posts";
    }
}
