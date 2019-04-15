import com.jfoenix.controls.JFXTabPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TabInput {
//	TabPane tabpane;
	JFXTabPane tabpane;
	Tab tab1 = new Tab("Location");
	Tab tab2 = new Tab("Coords");
	
	private final TextField longitude = new TextField();
	private final TextField latitude = new TextField();
	private final Text lonError = new Text();
	private final Text latError = new Text();
	private final TextField location = new TextField();
	private final Text locError = new Text();
	private final TextField date = new TextField();
	
	// This method sets up the content in the tabs and returns the tabpane
	// so it can be added to the main gui
	public TabPane tabpane() {
//		tabpane = new TabPane();
		tabpane = new JFXTabPane();
		tabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Insets inset = new Insets(0,15,0,15);

		tabpane.getTabs().add(tab1);
		tabpane.getTabs().add(tab2);
		
		location.setPromptText("Enter a location");
		locError.setFill(Color.RED);
		locError.setStyle("-fx-font: 12 arial");
		
        longitude.setPromptText("Enter the Longitude : -180 to 180");
        lonError.setFill(Color.RED);
        lonError.setStyle("-fx-font: 12 arial");
        latitude.setPromptText("Enter the Latitude : -90 to 90");
        latError.setFill(Color.RED);
        latError.setStyle("-fx-font: 12 arial");
        
        date.setPromptText("Enter the date (optional)");
        
        // Set the tab for Location input
        VBox tab1content = new VBox(15);
        tab1content.setAlignment(Pos.TOP_CENTER);
        tab1content.setMargin(location, new Insets(15,15,0,15));
        tab1content.getChildren().addAll(location, locError);
        tab1.setContent(tab1content);
        
        // Set the tab for Coordinates input
        VBox tab2content = new VBox(15);
        tab2content.setAlignment(Pos.TOP_CENTER);
        tab2content.setMargin(longitude, new Insets(15,15,0,15));
        tab2content.setMargin(latitude, inset);
        tab2content.getChildren().addAll(longitude, lonError, latitude, latError);
		tab2.setContent(tab2content);

		return tabpane;
	}

	// Getters and setters
	public String getLocation() {
		return location.getText();
	}
	
	public String getLongitude() {
		return longitude.getText();
	}

	public String getLatitude() {
		return latitude.getText();
	}

	public String getLonError() {
		return lonError.getText();
	}

	public String getLatError() {
		return latError.getText();
	}
	
	public void setLonError(String s) {
		lonError.setText(s);
	}
	
	public void setLatError(String s) {
		latError.setText(s);
	}
	
	public String getLocError() {
		return locError.getText();
	}
	
	public void setLocError(String s) {
		locError.setText(s);
	}
	
	public void setLongitude(String s) {
		longitude.setText(s);
	}
	
	public void setLatitude(String s) {
		latitude.setText(s);
	}
	
	public void setLocation(String s) {
		location.setText(s);
	}
}
