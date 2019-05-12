package com.astronomicaltimes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest {
	static Controller control;
	@BeforeEach
	void setUp() throws Exception {
		control = new Controller();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testInputValidate() {
		String lon = "43.65776767";
		String lat ="-4.7668";
		assertEquals(true, Controller.inputValidate(lon, lat));
		assertEquals(false, Controller.inputValidate("", lat));
		assertEquals(false, Controller.inputValidate(lon, ""));
		assertEquals(false, Controller.inputValidate("sdfs", "Sfesf"));
		assertEquals(false, Controller.inputValidate("546.34", lat));
		assertEquals(false, Controller.inputValidate(lon, "sdfkjhsf"));
		assertEquals(false, Controller.inputValidate(lon, "-108.45"));
	}
	
	@Test
	void testIsDouble() {
		String lon = "53.6565";
		assertEquals(true, Controller.isDouble(lon));
		assertEquals(false, Controller.isDouble("rgdrgr"));
	}

}
