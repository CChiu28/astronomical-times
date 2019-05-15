package com.astronomicaltimes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private Data data;
	private ObservableList<Results> tableList;

	// Retrieve data from Results class
	public ObservableList<Results> setToTable(Data results) {
		data = results;
		tableList = FXCollections.observableArrayList(data.getRes());
		return tableList;
	}


	public ObservableList<Results> getTable() {
		return tableList;
	}
	
	public TitledPane setHeader() {
		VBox box = new VBox(5);
		box.setAlignment(Pos.CENTER_RIGHT);
				
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
		
		TitledPane node = new TitledPane("Astronomical Times", box);
		node.setContent(box);
		node.setCollapsible(false);
		
		return node;
	}
	
	public TitledPane setInfo(String head, Results res) {
//		TitledPane node = new TitledPane(head);
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
	private void setLabelStyle(Label[] arr ) {
		for (Label label : arr) {
			label.setTextFill(Color.WHITE);
		}
	}
}
