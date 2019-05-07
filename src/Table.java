import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 * This class is used to populate a table with the information received from the
 * SunriseSunset API
 * This class returns a TableView to MainScene
 */
public class Table {
	private TableView<Results> view;
	private Data data;
	private ObservableList<Results> tableList;
	
	public TableView<Results> table() {
		view = new TableView<Results>();
		view.setItems(tableList);
		view.setEditable(false);
		
		// Create table columns
		TableColumn<Results, String> sunriseCol = new TableColumn<Results, String>("Sunrise");
		TableColumn<Results, String> sunsetCol = new TableColumn<Results, String>("Sunset");
		TableColumn<Results, String> solCol = new TableColumn<Results, String>("Solar Noon");
		TableColumn<Results, String> dayCol = new TableColumn<Results, String>("Day Length");
		TableColumn<Results, String> twibeginCol = new TableColumn<Results, String>("Civil Twilight Begins");
		TableColumn<Results, String> twiendCol = new TableColumn<Results, String>("Civil Twilight Ends");
		TableColumn<Results, String> naubeginCol = new TableColumn<Results, String>("Naughtical Twilight Begins");
		TableColumn<Results, String> nauendCol = new TableColumn<Results, String>("Naughtical Twilight Ends");
		TableColumn<Results, String> astbeginCol = new TableColumn<Results, String>("Astronomical Twilight Begins");
		TableColumn<Results, String> astendCol = new TableColumn<Results, String>("Astronomoical Twilight Ends");
		
		// Set columns to read the specific values in Results class
		sunriseCol.setCellValueFactory(new PropertyValueFactory<Results, String>("sunrise"));
		sunsetCol.setCellValueFactory(new PropertyValueFactory<Results, String>("Sunset"));
		solCol.setCellValueFactory(new PropertyValueFactory<Results, String>("solar_noon"));
		dayCol.setCellValueFactory(new PropertyValueFactory<Results, String>("day_length"));
		twibeginCol.setCellValueFactory(new PropertyValueFactory<Results, String>("civil_twilight_begin"));
		twiendCol.setCellValueFactory(new PropertyValueFactory<Results, String>("civil_twilight_end"));
		naubeginCol.setCellValueFactory(new PropertyValueFactory<Results, String>("nautical_twilight_begin"));
		nauendCol.setCellValueFactory(new PropertyValueFactory<Results, String>("nautical_twilight_end"));
		astbeginCol.setCellValueFactory(new PropertyValueFactory<Results, String>("astronomical_twilight_begin"));
		astendCol.setCellValueFactory(new PropertyValueFactory<Results, String>("astronomical_twilight_end"));
		
		// Add columns to table
		view.getColumns().addAll(sunriseCol, sunsetCol, solCol, dayCol, twibeginCol, twiendCol, naubeginCol, nauendCol, astbeginCol, astendCol);
		
//		view.getItems().add(data.res());
		return view;
	}
	
	// Retrieve data from Results class
	public void setToTable(Data results) {
		data = results;
		tableList = FXCollections.observableArrayList(data.res());
	}
	
	public void addToTable() {
		
	}
}
