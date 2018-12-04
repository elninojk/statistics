package com.n26.statistics.dto;

public class TransactionDto{

	Double amount;
	Long timeStamp;
	public TransactionDto(double amount, long timeStamp) {
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
