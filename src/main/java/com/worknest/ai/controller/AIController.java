package com.worknest.ai.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worknest.ai.service.QueryService;

@RestController
@RequestMapping("/bot")
public class AIController {

	private QueryService queryService;
	
	public AIController(QueryService queryService) {
		this.queryService = queryService;
	}
	
	@PostMapping("/ask")
	public Map<String, Object> askQuestion(@RequestBody Map<String, String> body){
		String question = body.get("question");
		return queryService.runUserQuery(question);
	}
	
//	@GetMapping("/ask")
//	public Map<String, Object> askQuestion(@RequestParam("question") String question) {
//	    return queryService.runUserQuery(question);
//	}

}
