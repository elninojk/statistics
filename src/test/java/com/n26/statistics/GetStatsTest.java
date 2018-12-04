/**
 * 
 */
package com.n26.statistics;

import static org.junit.Assert.*;

import java.util.PriorityQueue;
import java.util.Queue;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.n26.statistics.controllers.Transaction;

/**
 * @author jerilkuruvila
 *
 */
@RunWith(SpringRunner.class)
public class GetStatsTest {

	private static RequestSpecification request;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		RestAssured.baseURI ="http://localhost:8080";
		request = RestAssured.given();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws JSONException {
		Queue<Transaction> transactions = new PriorityQueue<>();
		transactions.add(new Transaction(100, System.currentTimeMillis()));
		for(Transaction tra: transactions)
		{
			JSONObject obj = new JSONObject();
			obj.put("amount",tra.getAmount());
			obj.put("timeStamp",tra.getTimeStamp());
			request.body(obj.toString());
			
			request.header("Content-Type", "application/json");
			Response response = request.post("/transactions");
		}
		get("/statistics").then().assertThat().body("sum", equalTo("100.0"));
	}

	
	//Check for O(1) post
	// Check for O(1) get
	
	//give old value and check
	//give correct value and check
	
	//give a lot of correct and wrong 
	
	//post
	
	//get
	
	//check sum,avg,max,min,count
	
}
