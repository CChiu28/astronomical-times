package com.astronomicaltimes;

import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/*
 * This class sets up the content of the Forecast Tab.
 * It returns the 5 day forecast of astronomical times
 */
public class ForecastTab {
	private Data info;
	private GetData getdata = new GetData();
	private final int NUM_OF_DAYS = 5;
	
	// This method creates NUM_OF_DAYS of TitledPane to add to the ListView
	public void setCell(GetData get, ObservableList<TitledPane> list, Data data, String location, String lat, String lng, LocalDate date, int check) {
		info = data;
		TitledPane header = setHeader(get);
		list.add(header);
		try {
			for (int i=0; i<NUM_OF_DAYS; i++) {
				list.add(getCast(info,date.toString()));
				date = date.plusDays(1);
				info = getdata.sendGET(lat, lng, location, date.toString(), check);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// This method sets up and returns a TitledPane for one date
	public TitledPane getCast(Data data, String date) {
		VBox box = new VBox(5);
		
		Label sunrise = new Label(data.getRes().getSunrise());
		Label sunset = new Label(data.getRes().getSunset());
		Label solNoon = new Label(data.getRes().getSolar_noon());
		Label dayLength = new Label(data.getRes().getDay_length()+" hours");
		Label civilBegin = new Label(data.getRes().getCivil_twilight_begin());
		Label nauBegin = new Label(data.getRes().getNautical_twilight_begin());
		Label astBegin = new Label(data.getRes().getAstronomical_twilight_begin());
		Label civilEnd = new Label(data.getRes().getCivil_twilight_end());
		Label nauEnd = new Label(data.getRes().getNautical_twilight_end());
		Label astEnd = new Label(data.getRes().getAstronomical_twilight_end());
		
		Label[] arr = new Label[] { sunrise, sunset, solNoon, dayLength, civilBegin, nauBegin, astBegin, civilEnd, nauEnd, astEnd };
		setLabelStyle(arr);
		
		box.getChildren().addAll(sunrise,sunset,solNoon,dayLength,civilBegin,nauBegin,astBegin,civilEnd,nauEnd,astEnd);
		
		TitledPane node = new TitledPane(date, box);
		node.setCollapsible(false);
		
		return node;
	}
	
	// This method sets up the header TitledPane
	private TitledPane setHeader(GetData get) {
		VBox box = new VBox(5);
		
		Label sunrise = new Label("Sunrise");
		Label sunset = new Label("Sunset");
		Label solNoon = new Label("Solar Noon");
		Label dayLength = new Label("Day Length");
		Label civilBegin = new Label("Civil Twilight Begins");
		Label nauBegin = new Label("Nautical Twilight Begins");
		Label astBegin = new Label("Astronomical Twilight Begins");
		Label civilEnd = new Label("Civil Twilight Ends");
		Label nauEnd = new Label("Nautical Twilight Ends");
		Label astEnd = new Label("Astronomical Twilight Ends");
		Label[] arr = new Label[] { sunrise, sunset, solNoon, dayLength, civilBegin, nauBegin, astBegin, civilEnd, nauEnd, astEnd };
		setLabelStyle(arr);
		
		box.getChildren().addAll(sunrise,sunset,solNoon,dayLength,civilBegin,nauBegin,astBegin,civilEnd,nauEnd,astEnd);
		
		TitledPane node = new TitledPane(get.getDisplayName(), box);
		node.setCollapsible(false);
		
		return node;
	}
	
	// Set up the color for all the Labels
	private void setLabelStyle(Label[] arr ) {
		for (Label label : arr) {
			label.setTextFill(Color.WHITE);
		}
	}
}
