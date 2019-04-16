import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainScene {
//	TabPane mainTab;
	JFXTabPane mainTab;
	Scene mainscene;
	public Scene mainScene() {
//		mainTab = new TabPane();
		mainTab = new JFXTabPane();
		mainTab.setVisible(false);
		mainTab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		Tab mainOutput = new Tab("Output");
		Tab compareTab = new Tab("Compare");
		Tab helpTab = new Tab("Definitions");
		Tab aboutTab = new Tab("About");
		mainTab.getTabs().addAll(mainOutput,compareTab, helpTab, aboutTab);
		
		VBox mainBox = new VBox(10);
		VBox submitLayout = new VBox(10);
		
		GridPane mainOutPane = new GridPane();
		
		TabInput tabpane = new TabInput();
		Table table = new Table();
		About about = new About();
		
		final TextField date = new TextField();
		date.setPromptText("Date");
		date.setPrefColumnCount(20);
		date.getText();
//		DatePicker datepicker = new DatePicker();
		JFXDatePicker datepicker = new JFXDatePicker();
		datepicker.setPromptText("Pick a date");
		datepicker.setEditable(false);
		
		//SUBMIT BUTTON
		
		final JFXButton submit = new JFXButton ("Submit");
		GridPane.setConstraints(submit, 5, 18);
		
		//OUTPUT TEXT
		
		final Text output = new Text();
		output.setFill(Color.WHITE);
		mainOutPane.add(output, 0, 0);
		
		// Set content for Main output and About tabs
		mainOutput.setContent(mainOutPane);
		aboutTab.setContent(about.about());
		
		// Set layout for the input Tabs
		submitLayout.setAlignment(Pos.CENTER);
		submitLayout.getChildren().addAll(tabpane.tabpane(),datepicker,submit);
		submitLayout.setPadding(new Insets(0,300,0,300));
		
		// Set input layout and output layout to main layout
		mainBox.getChildren().addAll(submitLayout,mainTab);
		
		mainscene = new Scene(mainBox);
		mainscene.getStylesheets().add("displayStyle.css");
        
		/* This is the event handler for the submit button.
		 * Contains all the work when button is pressed.
		 */
		submit.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	LocalDate dateval = LocalDate.now();
		    	getData getdata = new getData();
		    	Data results;
		    	if (datepicker.getValue()!=null) {
					dateval = datepicker.getValue();
				}
		        if (inputValidate(tabpane.getLongitude(), tabpane.getLatitude()) && tabpane.getLocation().isEmpty()) {
		
					try {
					    /* Changes the scene to resultScene.
					     * Takes the inputs from gui and converts into doubles.
					     * These values are then passed into the getData obj to connect to API.
					     * Results are mapped into a Data obj and getAll() is called.
					     * This needs to be reworked so results are outputed into
					     * the gui instead of console. The getters in Data will have to be used here.*/
//						LocalDate dateval = LocalDate.now();
						System.out.println(dateval.toString());
					    results = getdata.sendGET(Double.parseDouble(tabpane.getLatitude()), Double.parseDouble(tabpane.getLongitude()), dateval.toString());
					    System.out.println("Latitude: "+tabpane.getLatitude());
					    System.out.println("Longitude: "+tabpane.getLongitude());
					
					    //Displays Output to Gui
					    output.setText("Longitude: "+tabpane.getLongitude()+"\n"+"Latitude: "+tabpane.getLatitude()+"\n"+dateval+"\n"+results.displayOutPut());
//					    mainOutPane.add(output, 0, 0);
//					    mainOutput.setContent(mainOutPane);
					    table.setToTable(results);
					    clearInput(tabpane);
					    compareTab.setContent(table.table());
					    mainTab.requestLayout();
					    mainTab.setVisible(true);
					} catch (Exception e1) {
					    // TODO Auto-generated catch block
					    e1.printStackTrace();
					}
		    } else if ((tabpane.getLongitude().isEmpty()&&tabpane.getLatitude().isEmpty())&&!tabpane.getLocation().isEmpty()) {
		    	try {
		    		results = getdata.sendGET(tabpane.getLocation(), dateval.toString());
		    		output.setText("Location: "+tabpane.getLocation()+"\n"+dateval+"\n"+results.displayOutPut());
					table.setToTable(results);
					clearInput(tabpane);
					compareTab.setContent(table.table());
					mainTab.requestLayout();
					mainTab.setVisible(true);
		    	} catch (Exception e2) {
		    		tabpane.setLocError("Invalid location");
		    	}
		    }
		        else {
		        if (tabpane.getLongitude().isEmpty() || !isDouble(tabpane.getLongitude()) || Double.parseDouble(tabpane.getLongitude())>180 || Double.parseDouble(tabpane.getLongitude())<-180)
		        	tabpane.setLonError("Please enter a valid longitude value");
		        if (tabpane.getLatitude().isEmpty() || !isDouble(tabpane.getLatitude()) || Double.parseDouble(tabpane.getLatitude())>90 || Double.parseDouble(tabpane.getLatitude())<-90)
		        	tabpane.setLatError("Please enter a valid latitude value");
		        if (tabpane.getLocation().isEmpty())
		        	tabpane.setLocError("Invalid location");
		        }
		    }
		});
        return mainscene;
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
	static boolean inputValidate(String lon, String lat) {
		if (lon.isEmpty() || lat.isEmpty())
			return false;
		if (!isDouble(lon) || !isDouble(lat))
			return false;
		if (Double.parseDouble(lon)>180 || Double.parseDouble(lon)<-180)
			return false;
		if (Double.parseDouble(lat)>90 || Double.parseDouble(lat)<-90)
			return false;
		return true;
	}
	
	/** {@link #isDouble(String)}
	 * @param str
	 * @return boolean
	 * This method checks if the longitude and latitude fields are able to be parsed as Doubles
	 */
	static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	static void clearInput(TabInput input) {
		input.setLocation("");
		input.setLongitude("");
		input.setLatitude("");
		input.setLatError("");
		input.setLonError("");
		input.setLocError("");
	}
}
