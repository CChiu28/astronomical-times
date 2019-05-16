package com.astronomicaltimes;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/*
 * This class is used to populate a table with the information received from the
 * SunriseSunset API
 * It also allows the user to add different locations to the table
 * This class returns a VBox to MainScene
 */
public class Compare {
	
	// This method sets a header TitledPane
	public TitledPane setHeader() {
		VBox box = new VBox(5);
		box.setAlignment(Pos.CENTER_RIGHT);
				
		final Label sunrise = new Label("Sunrise");
		final Label sunset = new Label("Sunset");
		final Label solNoon = new Label("Solar Noon");
		final Label dayLength = new Label("Day Length");
		Label civilBegin = new Label("Civil Twilight Begins");
		Label nauBegin = new Label("Nautical Twilight Begins");
		Label astBegin = new Label("Astronomical Twilight Begins");
		Label civilEnd = new Label("Civil Twilight Ends");
		Label nauEnd = new Label("Nautical Twilight Ends");
		Label astEnd = new Label("Astronomical Twilight Ends");
		Label[] arr = new Label[] { sunrise, sunset, solNoon, dayLength, civilBegin, nauBegin, astBegin, civilEnd, nauEnd, astEnd };
		setLabelStyle(arr);
		
		box.getChildren().addAll(sunrise,sunset,solNoon,dayLength,civilBegin,nauBegin,astBegin,civilEnd,nauEnd,astEnd);
		
		TitledPane node = new TitledPane("Astronomical Times", box);
		node.setContent(box);
		node.setCollapsible(false);
		
		return node;
	}
	
	// This method is calls to add a new entry to the Compare List
	public TitledPane setInfo(String head, Results res) {
		VBox box = new VBox(5);
		box.setAlignment(Pos.CENTER);
		
		Label sunrise = new Label(res.getSunrise());
		Label sunset = new Label(res.getSunset());
		Label solNoon = new Label(res.getSolar_noon());
		Label dayLength = new Label(res.getDay_length()+" hours");
		Label civilBegin = new Label(res.getCivil_twilight_begin());
		Label nauBegin = new Label(res.getNautical_twilight_begin());
		Label astBegin = new Label(res.getAstronomical_twilight_begin());
		Label civilEnd = new Label(res.getCivil_twilight_end());
		Label nauEnd = new Label(res.getNautical_twilight_end());
		Label astEnd = new Label(res.getAstronomical_twilight_end());
		Label[] arr = new Label[] { sunrise, sunset, solNoon, dayLength, civilBegin, nauBegin, astBegin, civilEnd, nauEnd, astEnd };
		setLabelStyle(arr);
		
		box.getChildren().addAll(sunrise,sunset,solNoon,dayLength,civilBegin,nauBegin,astBegin,civilEnd,nauEnd,astEnd);
		
		TitledPane node = new TitledPane(head, box);
		node.setCollapsible(false);
		
		return node;
	}
	
	// This method sets up styling for the Labels
	private void setLabelStyle(Label[] arr ) {
		for (Label label : arr) {
			label.setTextFill(Color.WHITE);
		}
	}
}
