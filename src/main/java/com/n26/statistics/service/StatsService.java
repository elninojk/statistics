package com.n26.statistics.service;

import java.util.DoubleSummaryStatistics;
import java.util.PriorityQueue;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.n26.statistics.controllers.Statistics;
import com.n26.statistics.controllers.Transaction;
import com.n26.statistics.controllers.TransactionInvalidException;

@Service
public class StatsService {
	
	@Value("${statsBasedOnSeconds}")
    private long statsBasedOnSeconds;
	private static final Queue<Transaction> TRANSACTION_QUEUE = new PriorityQueue<>();
	private Statistics stats;
	private Object lock = new Object();
	
	public Statistics getStats()
	{
		return stats;
	}
	
	public void addTransacation(Transaction transaction) throws TransactionInvalidException
	{
		if((System.currentTimeMillis()  -transaction.getTimeStamp()) >statsBasedOnSeconds)
		{
			throw new TransactionInvalidException();
		}
		
		synchronized (lock) {
			TRANSACTION_QUEUE.add(transaction);
		}
		
		
	}
	
	private void clearOld()
	{
		synchronized (lock) {
			TRANSACTION_QUEUE.removeIf(transcation -> (System.currentTimeMillis() -transcation.getTimeStamp()) >statsBasedOnSeconds );
		}
		
	}
	
	@Async
	public void calculateStats()
	{
		DoubleSummaryStatistics  stat =TRANSACTION_QUEUE.stream().mapToDouble(value -> value.getAmount()).summaryStatistics();
		stats = new Statistics(stat.getSum(), stat.getAverage(), stat.getMax(), stat.getMin(), stat.getCount());
	}
	
	@Scheduled(fixedRate = 60000, initialDelay = 5000)
	public void generateReports()
	{
		clearOld();
		calculateStats();
	}
	
}
