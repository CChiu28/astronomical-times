package com.astronomicaltimes;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

class ForecastTabTest {
	ForecastTab forecast;
	JFXPanel fxPanel;
	TitledPane pane;
	GetData getdata;
	ObservableList<TitledPane> list;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		forecast = new ForecastTab();
		fxPanel = new JFXPanel();
		getdata = new GetData();
		list = FXCollections.observableArrayList();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSetCell() throws Exception {
		Data data = getdata.sendGET("", "", "new york", "", 0);
		LocalDate now = LocalDate.now();
		forecast.setCell(getdata, list, data, "brooklyn, ny", "", "", now, 0);
		pane = list.get(0);
		VBox vbox = (VBox) pane.getContent();
		Label label = (Label) vbox.getChildren().get(0);
		assertTrue(label.getText()=="Sunrise");
	}

	@Test
	void testGetCast() {
		Data data = new Data();
		data.getRes().setVal("sunrise", "test");
		String date = "date";
		pane = forecast.getCast(data, date);
		VBox vbox = (VBox) pane.getContent();
		Label label = (Label) vbox.getChildren().get(0);
		System.out.println(label);
		assertTrue(label.getText()=="test");
	}

}
