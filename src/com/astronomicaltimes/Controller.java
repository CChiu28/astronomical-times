package com.astronomicaltimes;

import java.time.LocalDate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.sun.prism.paint.Color;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/*
 * This is the Controller file that links all the FXML components with Java.
 */
public class Controller {
	private Data results;
	private Results res = new Results();
	private GetData getdata;
	private LocalDate dateval;
	private Compare table;
	private ForecastTab forecast;
	private ObservableList<Results> tableList;
	private ObservableList<TitledPane> list;
	private ObservableList<GetData> locList;
	private ObservableList<String> resloc;
	private final About about = new About();
	private final Definitions def = new Definitions();
	
	private final int inputIsLocation = 0;
	private final int inputIsCoord = 1;
	private final int maxLatitude = 90;
	private final int minLatitude = -90;
	private final int maxLongitude = 180;
	private final int minLongitude = -180;
	
	@FXML
	private JFXTabPane resultsTabPane;
	@FXML
	private Tab aboutTab, defTab;
	
	@FXML
	private JFXTextField locationField, latField, lngField, compareLocation;
	private RequiredFieldValidator validator;
	
	@FXML
	private JFXDatePicker datepicker, compareDate;
	
	@FXML
	private JFXButton inputBtn, compareBtn;
	
	@FXML
	private JFXListView<String> resultLoc;
	
	@FXML
	private TableView<Results> compareTable;
	@FXML 
	private TableColumn<Results, String> sunriseCol, sunsetCol, civilBCol, nauBCol, astBCol, civilECol, nauECol, astECol, dayLengthCol, solNoonCol;
	private TableColumn<Results, String>[] tableArr = (TableColumn<Results,String>[]) new TableColumn[] {sunriseCol, sunsetCol, civilBCol, nauBCol, astBCol, civilECol, nauECol, astECol, dayLengthCol, solNoonCol};;
	 
	@FXML
	private TableColumn<GetData, String> locCol;
	@FXML
	private JFXListView<TitledPane> forecastList;
	@FXML
	private TableView<GetData> locListView;
	
	@FXML
	private Label sunriseTime,sunsetTime,civilBTime,civilETime,nauBTime,nauETime,astBTime,astETime, solTime, dayLengthTime;
	private Label[] arr = new Label[] {sunriseTime,sunsetTime,civilBTime,civilETime,nauBTime,nauETime,astBTime,astETime, solTime, dayLengthTime};
	
	@FXML
	private void handleButtonAction(ActionEvent e) {
		System.out.println(e);
		if (e.getSource()==inputBtn) {
			submitLoc();
		}
		if (e.getSource()==compareBtn) {
			addToTable();
		}
	}
	
    public void submitLoc() {
    	dateval = LocalDate.now();
    	if (datepicker.getValue()!=null) {
			dateval = datepicker.getValue();
		}
        if (inputValidate(lngField.getText(), latField.getText()) && locationField.getText().isEmpty()) {

			try {
			    /* Changes the scene to resultScene.
			     * Takes the inputs from gui and converts into doubles.
			     * These values are then passed into the getData obj to connect to API.
			     * Results are mapped into a Data obj and getAll() is called.
			     * This needs to be reworked so results are outputed into
			     * the gui instead of console. The getters in Data will have to be used here.*/
			    results = getdata.sendGET(latField.getText(), lngField.getText(), locationField.getText(), dateval.toString(), inputIsCoord);
			    System.out.println("Latitude: "+latField.getText());
			    System.out.println("Longitude: "+lngField.getText());

			    clean();
			    setResults(inputIsCoord);
			    resultsTabPane.setVisible(true);
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
	    } else if ((lngField.getText().isEmpty()&&latField.getText().isEmpty())&&!locationField.getText().isEmpty()) {
	    	try {
	    		results = getdata.sendGET(latField.getText(), lngField.getText(), locationField.getText(), dateval.toString(), inputIsLocation);
	    		clean();
	    		setResults(inputIsLocation);
	    		resultsTabPane.setVisible(true);
	    	} catch (Exception e2) {
	    		//tabpane.setLocError("Error");
	    		locationField.clear();
	    		locationField.validate();
	    		e2.printStackTrace();
	    	}
	    } else {
	        if (checkCoordInput(lngField, maxLongitude, minLongitude))
	        	lngField.clear();
	        	lngField.validate();
	        if (checkCoordInput(latField, maxLatitude, minLatitude))
	        	latField.clear();
	        	latField.validate();
	        if (locationField.getText().isEmpty())
	        	System.out.println("stuff");
	        }
    }
    
    boolean checkCoordInput(JFXTextField coord, int max, int min) {
    	if (coord.getText().isEmpty())
    		return true;
    	if (!isDouble(coord.getText()))
    		return true;
    	if (Double.parseDouble(coord.getText())>max)
    		return true;
    	if (Double.parseDouble(coord.getText())<min)
    		return true;
    	return false;
    }
    
	private void addToTable() {
		getdata = new GetData();
		LocalDate val = LocalDate.now();
		if (compareDate.getValue()!=null) {
			val = compareDate.getValue();
		}
		if (!compareLocation.toString().isEmpty()) {
			try {
				results = getdata.sendGET(latField.getText(), lngField.getText(), compareLocation.getText(), val.toString(), inputIsLocation);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tableList.add(results.getRes());
			locList.add(getdata);
		}
//		tableList.add(results.getRes());
//		locList.add(getdata);
	}

	/** {@link #inputValidate(String, String)}
	 * @param lon, lat
	 * @return boolean
	 * This method is used as input validation for the longitude and latitude fields
	 * It contains a series of checks that will return false if they pass.
	 * These checks include checking if the longitude and latitude strings are empty,
	 * if they fail as Doubles via the {@link #isDouble(String)} method, 
	 * and if they exceed the longitude and latitude boundaries (-90 to 90, -180 to 180)
	 */
	boolean inputValidate(String lon, String lat) {
		if (lon.isEmpty() || lat.isEmpty())
			return false;
		if (!isDouble(lon) || !isDouble(lat))
			return false;
		if (Double.parseDouble(lon)>maxLongitude || Double.parseDouble(lon)<minLongitude)
			return false;
		if (Double.parseDouble(lat)>maxLatitude || Double.parseDouble(lat)<minLatitude)
			return false;
		return true;
	}
	
	/** {@link #isDouble(String)}
	 * @param str
	 * @return boolean
	 * This method checks if the longitude and latitude fields are able to be parsed as Doubles
	 */
	boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private void clean() {
		for (int i=0; i<arr.length; i++) {
			arr[i] = new Label("");
		}
		tableList.clear();
		list.clear();
		locList.clear();
		resloc.clear();
	}
	
	void setResults(int inputCheck) {
		res = results.getRes();
		resloc.add(getdata.getDisplayName());
		resloc.add("Lat: "+getdata.getLat());
		resloc.add("Lng: "+getdata.getLng());
		resloc.add(dateval.toString());
		solTime.setText(res.getSolar_noon());
		dayLengthTime.setText(res.getDay_length()+" hours");
		sunriseTime.setText(res.getSunrise());
		sunsetTime.setText(res.getSunset());
		civilBTime.setText(res.getCivil_twilight_begin());
		civilETime.setText(res.getCivil_twilight_end());
		nauBTime.setText(res.getNautical_twilight_begin());
		nauETime.setText(res.getNautical_twilight_end());
		astBTime.setText(res.getAstronomical_twilight_begin());
		astETime.setText(res.getAstronomical_twilight_end());
		tableList.add(res);
		locList.add(getdata);
		forecast.setCell(getdata, list,results, locationField.getText(), latField.getText(), lngField.getText(), dateval, inputCheck);
		locationField.setText("");
		latField.setText("");
		lngField.setText("");
		
		resultsTabPane.requestLayout();
	}
	
	private void setColumns() {
		locCol.setCellValueFactory(new PropertyValueFactory<GetData, String>("displayName"));
		sunriseCol.setCellValueFactory(new PropertyValueFactory<Results, String>("Sunrise"));
		sunsetCol.setCellValueFactory(new PropertyValueFactory<Results, String>("Sunset"));
		solNoonCol.setCellValueFactory(new PropertyValueFactory<Results, String>("solar_noon"));
		dayLengthCol.setCellValueFactory(new PropertyValueFactory<Results, String>("day_length"));
		civilBCol.setCellValueFactory(new PropertyValueFactory<Results, String>("civil_twilight_begin"));
		civilECol.setCellValueFactory(new PropertyValueFactory<Results, String>("civil_twilight_end"));
		nauBCol.setCellValueFactory(new PropertyValueFactory<Results, String>("nautical_twilight_begin"));
		nauECol.setCellValueFactory(new PropertyValueFactory<Results, String>("nautical_twilight_end"));
		astBCol.setCellValueFactory(new PropertyValueFactory<Results, String>("astronomical_twilight_begin"));
		astECol.setCellValueFactory(new PropertyValueFactory<Results, String>("astronomical_twilight_end"));
	}
	
	private void setValidator(JFXTextField field) {
		field.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(
				ObservableValue<? extends Boolean> arg0,
				Boolean oldPropertyValue, Boolean newPropertyValue) {
					if (!newPropertyValue) {
						field.validate();
					}
			}
		});
	}
	
	@FXML
	private void initialize() {
		table = new Compare();
		forecast = new ForecastTab();
		res = new Results();
		getdata = new GetData();
		setColumns();
		resultsTabPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5)");
		tableList = FXCollections.observableArrayList();
		compareTable.setItems(tableList);
		list = FXCollections.observableArrayList();
		forecastList.setItems(list);
		locList = FXCollections.observableArrayList();
		locListView.setItems(locList);
		resloc = FXCollections.observableArrayList();
		resultLoc.setItems(resloc);
		validator = new RequiredFieldValidator();
		validator.setMessage("Invalid input");
		locationField.getValidators().add(validator);
		latField.getValidators().add(validator);
		lngField.getValidators().add(validator);
		setValidator(locationField);
		setValidator(latField);
		setValidator(lngField);

		
		aboutTab.setContent(about.about());
		defTab.setContent(def.setDef());
	}


}
