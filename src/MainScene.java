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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/*
 * This class sets up the main overall Scene layout of the app.
 * It initializes and sets to different layouts multiple classes:
 * InputTab: Sets up the user input section of the app
 * About: Sets up the About tab in the results
 * MainOutput: Sets up the main results tab to display the API's data
 * Compare: Sets up the table for multiple locaiton comparisons
 */
public class MainScene {
//	//TabPane mainTab;
//	private JFXTabPane mainTab;
//	private Scene mainscene;
//	public Scene mainScene() {
//		//mainTab = new TabPane();
//		mainTab = new JFXTabPane();
//		mainTab.setVisible(false);
//		mainTab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
//
//		// Create tabs for the results section
//		Tab mainOutput = new Tab("Output");
//		final Tab compareTab = new Tab("Compare");
//		Tab helpTab = new Tab("Definitions");
//		Tab aboutTab = new Tab("About");
//		mainTab.getTabs().addAll(mainOutput,compareTab, helpTab, aboutTab);
//
//		// Layouts for the main scene
//		// mainBox: contains the layout for all other layout panes
//		// submitLayout: contains the user input section
//		VBox mainBox = new VBox(10);
//		VBox submitLayout = new VBox(10);
//
//		final GridPane mainOutPane = new GridPane();
//
//		// Initialize all other layout sections
//		final InputTab tabpane = new InputTab();
//		final Compare table = new Compare();
//		About about = new About();
//		final MainOutput mainoutput = new MainOutput();
//
//		final TextField date = new TextField();
//		date.setPromptText("Date");
//		date.setPrefColumnCount(20);
//		date.getText();
//		//DatePicker datepicker = new DatePicker();
//		final JFXDatePicker datepicker = new JFXDatePicker();
//		datepicker.setPromptText("Pick a date");
//		datepicker.setEditable(false);
//
//		//SUBMIT BUTTON
//
//		final JFXButton submit = new JFXButton ("Submit");
//		GridPane.setConstraints(submit, 5, 18);
//
//		//OUTPUT TEXT
//		/*
//		final Text output = new Text();
//		output.setFill(Color.WHITE);
//		mainOutPane.add(mainoutput.Labels(), 0, 0);*/
//
//		// Set content for Main output and About tabs
//		mainOutput.setContent(mainOutPane);
//		aboutTab.setContent(about.about());
//
//		// Set layout for the user input Tabs
//		submitLayout.setAlignment(Pos.CENTER);
//		submitLayout.getChildren().addAll(tabpane.tabpane(),datepicker,submit);
//		submitLayout.setPadding(new Insets(0, 300, 0, 300));
//
//		// Set input layout and output layout to main layout
//		mainBox.getChildren().addAll(submitLayout,mainTab);
//
//		mainscene = new Scene(mainBox);
//		mainscene.getStylesheets().add("displayStyle.css");
//
//		/* This is the event handler for the submit button.
//		 * Contains all the work when button is pressed.
//		 */
//		submit.setOnAction(new EventHandler<ActionEvent>() {
//		    public void handle(ActionEvent e) {
//		    	LocalDate dateval = LocalDate.now();
//		    	getData getdata = new getData();
//		    	Data results;
//		    	if (datepicker.getValue()!=null) {
//					dateval = datepicker.getValue();
//				}
//		    	mainOutPane.getChildren().clear();
//		    	mainOutPane.add(mainoutput.Labels(), 0, 0);
//		        if (inputValidate(tabpane.getLongitude(), tabpane.getLatitude()) && tabpane.getLocation().isEmpty()) {
//
//					try {
//					    /* Changes the scene to resultScene.
//					     * Takes the inputs from gui and converts into doubles.
//					     * These values are then passed into the getData obj to connect to API.
//					     * Results are mapped into a Data obj and getAll() is called.
//					     * This needs to be reworked so results are outputed into
//					     * the gui instead of console. The getters in Data will have to be used here.*/
//					    results = getdata.sendGET(tabpane.getLatitude(), tabpane.getLongitude(), dateval.toString());
//					    System.out.println("Latitude: "+tabpane.getLatitude());
//					    System.out.println("Longitude: "+tabpane.getLongitude());
//
//					    //Displays Output to Gui
////					    output.setText("");
////					    output.setText("Longitude: "+tabpane.getLongitude()+"\n"+"Latitude: "+tabpane.getLatitude()+"\n"+dateval+"\n"+results.displayOutPut());
//					    mainOutPane.add(mainoutput.Output(results.res()), 1, 0);
//					    setResults(results, table, compareTab, mainTab, tabpane);
//					} catch (Exception e1) {
//					    // TODO Auto-generated catch block
//					    e1.printStackTrace();
//					}
//			    } else if ((tabpane.getLongitude().isEmpty()&&tabpane.getLatitude().isEmpty())&&!tabpane.getLocation().isEmpty()) {
//			    	try {
//			    		results = getdata.sendGET(tabpane.getLocation(), dateval.toString());
//			    		mainoutput.clean();
////			    		output.setText("Location: "+tabpane.getLocation()+"\n"+dateval+"\n"+results.displayOutPut());
//			    		mainOutPane.add(mainoutput.Output(results.res()), 1, 0);
//			    		setResults(results, table, compareTab, mainTab, tabpane);
//			    	} catch (Exception e2) {
//			    		//tabpane.setLocError("Error");
//			    		e2.printStackTrace();
//			    	}
//			    } else {
//			        if (tabpane.getLongitude().isEmpty() || !isDouble(tabpane.getLongitude()) || Double.parseDouble(tabpane.getLongitude())>180 || Double.parseDouble(tabpane.getLongitude())<-180)
//			        	tabpane.setLonError("Please enter a valid longitude value");
//			        if (tabpane.getLatitude().isEmpty() || !isDouble(tabpane.getLatitude()) || Double.parseDouble(tabpane.getLatitude())>90 || Double.parseDouble(tabpane.getLatitude())<-90)
//			        	tabpane.setLatError("Please enter a valid latitude value");
//			        if (tabpane.getLocation().isEmpty())
//			        	tabpane.setLocError("Invalid location");
//			        }
//		    }
//		});
//        return mainscene;
//	}
//
//	/** {@link #inputValidate(String, String)}
//	 * @param lon, lat
//	 * @return boolean
//	 * This method is used as input validation for the longitude and latitude fields
//	 * It contains a series of checks that will return false if they pass.
//	 * These checks include checking if the longitude and latitude strings are empty,
//	 * if they fail as Doubles via the {@link #isDouble(String)} method,
//	 * and if they exceed the longitude and latitude boundaries (-90 to 90, -180 to 180)
//	 */
//	static boolean inputValidate(String lon, String lat) {
//		if (lon.isEmpty() || lat.isEmpty())
//			return false;
//		if (!isDouble(lon) || !isDouble(lat))
//			return false;
//		if (Double.parseDouble(lon)>180 || Double.parseDouble(lon)<-180)
//			return false;
//		if (Double.parseDouble(lat)>90 || Double.parseDouble(lat)<-90)
//			return false;
//		return true;
//	}
//
//	/** {@link #isDouble(String)}
//	 * @param str
//	 * @return boolean
//	 * This method checks if the longitude and latitude fields are able to be parsed as Doubles
//	 */
//	static boolean isDouble(String str) {
//		try {
//			Double.parseDouble(str);
//			return true;
//		} catch (NumberFormatException e) {
//			return false;
//		}
//	}
//
//	static void setResults(Data data, Compare table, Tab tab, JFXTabPane tabpane, InputTab tabinput) {
//		table.setToTable(data);
//		tab.setContent(table.compare());
//		tabpane.requestLayout();
//		tabpane.setVisible(true);
//		tabinput.setLocation("");
//		tabinput.setLongitude("");
//		tabinput.setLatitude("");
//		tabinput.setLatError("");
//		tabinput.setLonError("");
//		tabinput.setLocError("");
//	}
}
