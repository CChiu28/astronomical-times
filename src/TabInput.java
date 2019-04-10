import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TabInput {
	TabPane tabpane = new TabPane();
	Tab tab1 = new Tab("Location");
	Tab tab2 = new Tab("Coords");
	
	private final TextField longitude = new TextField();
	private final TextField latitude = new TextField();
	private final Text lonError = new Text();
	private final Text latError = new Text();
	private final TextField location = new TextField();
	
	// This method sets up the content in the tabs and returns the tabpane
	// so it can be added to the main gui
	public TabPane tabpane() {
		tabpane.getTabs().add(tab1);
		tabpane.getTabs().add(tab2);
		
		location.setPromptText("Enter a location");
		
        longitude.setPromptText("Enter the Longitude : -180 to 180");
        longitude.setPrefColumnCount(5);
        longitude.getText();

        lonError.setFill(Color.RED);
        lonError.setStyle("-fx-font: 11 arial");
        
        latitude.setPromptText("Enter the Latitude : -90 to 90");
        latitude.setPrefColumnCount(20);
        latitude.getText();
        latError.setFill(Color.RED);
        latError.setStyle("-fx-font: 11 arial");
        
        VBox tab1content = new VBox();
        tab1content.getChildren().addAll(location);
        tab1.setContent(tab1content);
        VBox tab2content = new VBox();
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
}
