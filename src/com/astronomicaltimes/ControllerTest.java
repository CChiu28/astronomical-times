package com.astronomicaltimes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jfoenix.controls.JFXTextField;

class ControllerTest {
	Controller control;
	String test;
	
	@BeforeEach
	void setUp() throws Exception {
		control = new Controller();
		test = "";
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testInputValidate() {
		String lon = "43.65776767";
		String lat ="-4.7668";
		assertTrue(control.inputValidate(lon, lat));
		assertFalse(control.inputValidate("", lat));
		assertFalse(control.inputValidate(lon, ""));
		assertFalse(control.inputValidate("sdfs", "Sfesf"));
		assertFalse(control.inputValidate("546.34", lat));
		assertFalse(control.inputValidate(lon, "sdfkjhsf"));
		assertFalse(control.inputValidate(lon, "-108.45"));
	}
	
	@Test
	void testIsDouble() {
		String lon = "53.6565";
		assertTrue(control.isDouble(lon));
		assertFalse(control.isDouble("rgdrgr"));
	}
	
	@Test
	void testCheckCoordInput() {
		int maxLat = 90;
		int minLat = -90;
		assertTrue(control.checkCoordInput(test, maxLat, minLat));
		test = new String("54.5345345");
		assertFalse(control.checkCoordInput(test, maxLat, minLat));
		test = "sdfsdf";
		assertTrue(control.checkCoordInput(test, maxLat, minLat));
		test = "95.45454";
		assertTrue(control.checkCoordInput(test, maxLat, minLat));
		test = "-95.45454";
		assertTrue(control.checkCoordInput(test, maxLat, minLat));
	}

}
