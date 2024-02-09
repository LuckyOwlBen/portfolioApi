package com.benco.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benco.portfolio.beans.responses.StartDiscussionResponse;
import com.benco.portfolio.services.StartDiscussionService;

@RestController
public class StartDiscussionController {

	public StartDiscussionController(StartDiscussionService startDiscussionService) {
		this.startDiscussionService = startDiscussionService;
	}

	private StartDiscussionService startDiscussionService;

	@GetMapping("startDiscussion")
	public ResponseEntity<StartDiscussionResponse> startDiscussion() {
		return startDiscussionService.generateStartDiscussionResponse();
	}
}
