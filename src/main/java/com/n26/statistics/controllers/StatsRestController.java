package com.n26.statistics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistics.dto.StatisticsDto;
import com.n26.statistics.dto.TransactionDto;
import com.n26.statistics.service.StatsService;

@RestController
public class StatsRestController {

	@Autowired
	private StatsService service;

	@GetMapping("/statistics")
	@ResponseStatus(HttpStatus.OK)
	public StatisticsDto get() {
		// O(1) since directly getting from service
		return service.getStats();
	}

	@PostMapping("/transactions")
	@ResponseStatus(HttpStatus.CREATED)
	public void addTransaction(@RequestBody TransactionDto transaction) {
		// O(1) since just adding transaction to queue;
		// calculateStats is async to guarantee O(1)
		service.addTransacation(transaction);
	}
}
