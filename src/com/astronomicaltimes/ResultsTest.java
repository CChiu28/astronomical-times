package com.astronomicaltimes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultsTest {
	Results res;
	final String[] arr = new String[] {"sunrise","sunset","solarNoon", "dayLength","civilBTime", "civilETime", "nauBTime", "nauETime", "astBTime", "astETime"};
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		res = new Results();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSetVal() {
		for (int i=0; i<arr.length; i++) {
			res.setVal(arr[i], arr[i]);
		}
		assertEquals(true, res.getSunrise()=="sunrise");
		assertEquals(true, res.getSunset()=="sunset");
		assertEquals(true, res.getSolar_noon()=="solarNoon");
		assertEquals(true, res.getDay_length()=="dayLength");
		assertEquals(true, res.getCivil_twilight_begin()=="civilBTime");
		assertEquals(true, res.getCivil_twilight_end()=="civilETime");
		assertEquals(true, res.getNautical_twilight_begin()=="nauBTime");
		assertEquals(true, res.getNautical_twilight_end()=="nauETime");
		assertEquals(true, res.getAstronomical_twilight_begin()=="astBTime");
		assertEquals(true, res.getAstronomical_twilight_end()=="astETime");
	}

	@Test
	void testCopy() {
		Results res2 = new Results();
		testSetVal();
		res2.copy(res);
		assertEquals(true, res.getSunrise()=="sunrise");
		assertEquals(true, res.getSunset()=="sunset");
		assertEquals(true, res.getSolar_noon()=="solarNoon");
		assertEquals(true, res.getDay_length()=="dayLength");
		assertEquals(true, res.getCivil_twilight_begin()=="civilBTime");
		assertEquals(true, res.getCivil_twilight_end()=="civilETime");
		assertEquals(true, res.getNautical_twilight_begin()=="nauBTime");
		assertEquals(true, res.getNautical_twilight_end()=="nauETime");
		assertEquals(true, res.getAstronomical_twilight_begin()=="astBTime");
		assertEquals(true, res.getAstronomical_twilight_end()=="astETime");
	}

}
