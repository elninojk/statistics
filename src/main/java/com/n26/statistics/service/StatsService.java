package com.n26.statistics.service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.n26.statistics.dto.StatisticsDto;
import com.n26.statistics.dto.TransactionDto;
import com.n26.statistics.exceptions.TransactionInvalidException;

@Service
public class StatsService {

	@Value("${statsBasedOnSeconds}")
	private long statsBasedOnSeconds;
	private static final List<TransactionDto> TRANSACTION_LIST = new ArrayList<>();
	private StatisticsDto stats;
	private Object lock = new Object();

	public StatisticsDto getStats() {
		return stats;
	}

	public void addTransacation(TransactionDto transaction) {
		if ((System.currentTimeMillis() - transaction.getTimeStamp()) > statsBasedOnSeconds) {
			throw new TransactionInvalidException();
		}

		synchronized (lock) {
			TRANSACTION_LIST.add(transaction);
			calculateStats();
		}

	}

	@Scheduled(fixedRate = 60000, initialDelay = 5000)
	public void clearOld() {
		synchronized (lock) {
			TRANSACTION_LIST.removeIf(
					transcation -> (System.currentTimeMillis() - transcation.getTimeStamp()) > statsBasedOnSeconds);
			calculateStats();
		}
	}

	@Async
	public void calculateStats() {
		DoubleSummaryStatistics stat = TRANSACTION_LIST.stream().mapToDouble(TransactionDto::getAmount)
				.summaryStatistics();
		stats = new StatisticsDto(stat.getSum(), stat.getAverage(), stat.getMax(), stat.getMin(), stat.getCount());
	}

}
