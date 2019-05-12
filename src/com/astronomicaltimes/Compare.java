package com.astronomicaltimes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

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

	// Adds new data to the table
	void addToTable(Data res, TableView<Results> table) {
		data = res;
		tableList.add(res.getRes());
	}

	public ObservableList<Results> getTable() {
		return tableList;
	}
}
