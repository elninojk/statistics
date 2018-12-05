/**
 * 
 */
package com.n26.statistics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.n26.statistics.dto.StatisticsDto;
import com.n26.statistics.dto.TransactionDto;

/**
 * @author jerilkuruvila
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetStatsTest {

	@Value("${statsBasedOnSeconds}")
	private long statsBasedOnSeconds;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test() throws JsonProcessingException, Exception {
		assertEquals(HttpStatus.NO_CONTENT,
				restTemplate.postForEntity("/transactions",
						new TransactionDto(100, System.currentTimeMillis() - (statsBasedOnSeconds + 100)), Object.class)
						.getStatusCode());
		ResponseEntity<Object> res = restTemplate.postForEntity("/transactions",
				new TransactionDto(100, System.currentTimeMillis()), Object.class);
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(Double.valueOf("100"),
				restTemplate.getForEntity("/statistics", StatisticsDto.class).getBody().getSum());
	}

	// Check for O(1) post
	// Check for O(1) get

	// give a lot of correct and wrong

	// post

	// get

	// check sum,avg,max,min,count

}
