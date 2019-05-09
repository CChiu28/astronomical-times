import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/*
 * This class is used to populate a table with the information received from the
 * SunriseSunset API
 * It also allows the user to add different locations to the table
 * This class returns a VBox to MainScene
 */
public class Compare {
	private TableView<Results> view;
	private Data data;
	private ObservableList<Results> tableList;
	private VBox vbox;
	private HBox hbox;
	private Label label;
	private JFXTextField text;
	private JFXButton button;
	private JFXDatePicker date;
	
	// This method sets up the layout for the Compare tab
	// Includes all necessary textfields, datepicker, button, and table
	public VBox compare() {
		vbox = new VBox();
		hbox = new HBox();
		text = new JFXTextField();
		text.setPromptText("Location");
		button = new JFXButton("Compare");
		date = new JFXDatePicker();
		date.setPromptText("Select a Date");
		date.setEditable(false);
		label = new Label("Enter another city to compare: ");
		label.setTextFill(Color.BEIGE);
		label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold");
		
		hbox.getChildren().addAll(label, text, button, date);
		
		vbox.getChildren().addAll(hbox, this.table());
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				LocalDate val = LocalDate.now();
				getData getdata = new getData();
				if (date.getValue()!=null) {
					val = date.getValue();
				}
				System.out.println(val.toString());
				if (!text.toString().isEmpty()) {
					System.out.println(text.getText());
					try {
						data = getdata.sendGET(text.getText(), val.toString());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				addToTable(data);
			}
		});
		return vbox;
	}
	
	// Set up general table and columns
	private TableView<Results> table() {
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
//		view.getItems().add(data.res());
	}
	
	// Adds new data to the table
	private void addToTable(Data res) {
		data = res;
		tableList.add(res.res());
	}
}
