package com.n26.statistics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistics.service.StatsService;

@RestController
public class StatsRestController {

	@Autowired
	StatsService service;

	@GetMapping("/statistics")
	public ResponseEntity<Statistics> get() {
		// O(1) since directly getting from service
		return new ResponseEntity<Statistics>(service.getStats(), HttpStatus.OK);
	}

	@PostMapping("/transactions")
	public ResponseEntity<Object> addTransaction(@RequestBody Transaction transaction) {
		
		try {
			// O(1) since just adding transaction to queue;
			// calculateStats is async to guarantee O(1)
			service.addTransacation(transaction);
			service.calculateStats();
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (TransactionInvalidException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
