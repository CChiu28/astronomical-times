package com.astronomicaltimes;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class getDataTest {
	static getData testData;
	static Data results;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testData = new getData();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSendGET() {
		String first = "54.34223";
		String second = "42.65343";
		String third = "";
		String date = "";
		Results res;
		int loc=0;
		int coord=1;
		try {
			results = testData.sendGET(first,second,third, date, coord);
			res = results.getRes();
			assertFalse(res.getAstronomical_twilight_begin().isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			results = testData.sendGET(first, second, "brooklyn college", date, loc);
			res = results.getRes();
			assertFalse(res.getAstronomical_twilight_begin().isEmpty());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
