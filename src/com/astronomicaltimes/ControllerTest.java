package com.astronomicaltimes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest {
	Controller control;
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
		assertEquals(true, control.inputValidate(lon, lat));
		assertEquals(false, control.inputValidate("", lat));
		assertEquals(false, control.inputValidate(lon, ""));
		assertEquals(false, control.inputValidate("sdfs", "Sfesf"));
		assertEquals(false, control.inputValidate("546.34", lat));
		assertEquals(false, control.inputValidate(lon, "sdfkjhsf"));
		assertEquals(false, control.inputValidate(lon, "-108.45"));
	}
	
	@Test
	void testIsDouble() {
		String lon = "53.6565";
		assertEquals(true, control.isDouble(lon));
		assertEquals(false, control.isDouble("rgdrgr"));
	}

}
