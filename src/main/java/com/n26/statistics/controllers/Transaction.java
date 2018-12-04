package com.n26.statistics.controllers;

public class Transaction{

	Double amount;
	Long timeStamp;
	public Transaction(double amount, long timeStamp) {
		this.amount = amount;
		this.timeStamp = timeStamp;
	}
	
	public Double getAmount() {
		return amount;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}
}
