package com.astronomicaltimes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;

//import static org.junit.Assert.*;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class CompareTest {
	Compare compare;
	TitledPane pane;
	JFXPanel fxPanel;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() throws Exception {
		compare = new Compare();
		fxPanel = new JFXPanel();
	}

	@AfterEach	
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetHeader() {
		pane = compare.setHeader();
		VBox vbox = (VBox) pane.getContent();
		Label label = (Label) vbox.getChildren().get(0);
		assertTrue(label.getText()=="Sunrise");
	}

	@Test
	public void testSetInfo() {
		Results res = new Results();
		res.setVal("sunrise", "test");
		pane = compare.setInfo("test", res);
		VBox vbox = (VBox) pane.getContent();
		Label label = (Label) vbox.getChildren().get(0);
		System.out.println(label);
		assertTrue(label.getText()=="test");
	}

}
