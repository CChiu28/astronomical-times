import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/*
 * This class is used to set up the user input section.
 * It contains a tabpane that includes tabs for Location (used by user to input a string location ie. New York, NY)
 * and a tab for specific coordinates
 */
public class InputTab {
	@FXML
	private JFXTextField locationField, latField, lngField;
	
//	//TabPane tabpane;
//	private JFXTabPane tabpane;
//	private Tab tab1 = new Tab("Location");
//	private Tab tab2 = new Tab("Coords");
//	
//	private final JFXTextField longitude = new JFXTextField();
//	private final JFXTextField latitude = new JFXTextField();
//	private final Text lonError = new Text();
//	private final Text latError = new Text();
//	private final JFXTextField location = new JFXTextField();
//	private final Text locError = new Text();
//	
//	// This method sets up the content in the tabs and returns the tabpane
//	// so it can be added to the main gui
//	public TabPane tabpane() {
//		//tabpane = new TabPane();
//		tabpane = new JFXTabPane();
//		tabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
//
//		tabpane.getTabs().add(tab1);
//		tabpane.getTabs().add(tab2);
//		
//		// Set up text fields for user input
//		location.setPromptText("Enter a location");
//		locError.setFill(Color.RED);
//		locError.setStyle("-fx-font: 12 arial");
//		
//        longitude.setPromptText("Enter the Longitude : -180 to 180");
//        lonError.setFill(Color.RED);
//        lonError.setStyle("-fx-font: 12 arial");
//        
//        latitude.setPromptText("Enter the Latitude : -90 to 90");
//        latError.setFill(Color.RED);
//        latError.setStyle("-fx-font: 12 arial");
//        
//        // Set the tab for Location input
//        VBox tab1content = new VBox(15);
//        tab1content.setAlignment(Pos.TOP_CENTER);
//        tab1content.setMargin(location, new Insets(15, 15, 0, 15));
//        tab1content.getChildren().addAll(location, locError);
//        tab1.setContent(tab1content);
//        
//        // Set the tab for Coordinates input
//        VBox tab2content = new VBox(15);
//        tab2content.setAlignment(Pos.TOP_CENTER);
//        tab2content.setMargin(longitude, new Insets(15, 15, 0, 15));
//        tab2content.setMargin(latitude, new Insets(0, 15, 0, 15));
//        tab2content.getChildren().addAll(longitude, lonError, latitude, latError);
//		tab2.setContent(tab2content);
//
//		return tabpane;
//	}
//
//	// Getters and setters
//	public String getLocation() {
//		return location.getText();
//	}
//	
//	public String getLongitude() {
//		return longitude.getText();
//	}
//
//	public String getLatitude() {
//		return latitude.getText();
//	}
//
//	public String getLonError() {
//		return lonError.getText();
//	}
//
//	public String getLatError() {
//		return latError.getText();
//	}
//	
//	public void setLonError(String s) {
//		lonError.setText(s);
//	}
//	
//	public void setLatError(String s) {
//		latError.setText(s);
//	}
//	
//	public String getLocError() {
//		return locError.getText();
//	}
//	
//	public void setLocError(String s) {
//		locError.setText(s);
//	}
//	
//	public void setLongitude(String s) {
//		longitude.setText(s);
//	}
//	
//	public void setLatitude(String s) {
//		latitude.setText(s);
//	}
//	
//	public void setLocation(String s) {
//		location.setText(s);
//	}
}
