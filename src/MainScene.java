import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainScene {
	TabPane mainTab;
	Scene mainscene;
	public Scene mainScene() {
		mainTab = new TabPane();
		mainTab.setVisible(false);
		mainTab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab mainOutput = new Tab("Output");
		Tab compareTab = new Tab("Compare");
		Tab helpTab = new Tab("Definitions");
		Tab aboutTab = new Tab("About");
		
		VBox mainBox = new VBox(10);
		GridPane submitLayout = new GridPane();
		submitLayout.setGridLinesVisible(true);
		submitLayout.setAlignment(Pos.TOP_CENTER);
		
		TabInput tabpane = new TabInput();
		Table table = new Table();
		
		final TextField date = new TextField();
		date.setPromptText("Date");
		date.setPrefColumnCount(20);
		date.getText();
		
		//SUBMIT BUTTON
		
		final Button submit = new Button ("Submit");
		GridPane.setConstraints(submit, 5, 18);
		
		//RESULTS SCENE
		
		VBox resultsLayout = new VBox(10);
		
		//RETURN TO DISPLAY SCENE BUTTON
		
		//        Button returnButton = new Button("Back");
		//        returnButton.setOnAction(e -> window.setScene(displayScene));
		
		//OUTPUT TEXT
		
		final Text output = new Text();
		output.setFill(Color.WHITE);
		
		mainTab.getTabs().addAll(mainOutput,compareTab, helpTab, aboutTab);
		
		submitLayout.add(tabpane.tabpane(), 1, 0);
		submitLayout.add(date, 1, 1);
		submitLayout.add(submit, 1,2);
		
		resultsLayout.getChildren().add(mainTab);
		resultsLayout.setAlignment(Pos.CENTER);
		
		mainBox.getChildren().addAll(submitLayout,resultsLayout);
		
		mainscene = new Scene(mainBox);
		mainscene.getStylesheets().add("displayStyle.css");
        
		/* This is the event handler for the submit button.
		 * Contains all the work when button is pressed.
		 */
		submit.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		
		        if (inputValidate(tabpane.getLongitude(), tabpane.getLatitude())) {
		
		            getData getdata = new getData(); // getData obj for API call
		
					try {
					    /* Changes the scene to resultScene.
					     * Takes the inputs from gui and converts into doubles.
					     * These values are then passed into the getData obj to connect to API.
					     * Results are mapped into a Data obj and getAll() is called.
					     * This needs to be reworked so results are outputed into
					     * the gui instead of console. The getters in Data will have to be used here.*/
					
					
					    Data results = getdata.sendGET(Double.parseDouble(tabpane.getLatitude()), Double.parseDouble(tabpane.getLongitude()), date.getText());
					    System.out.println("Latitude: "+tabpane.getLatitude());
					    System.out.println("Longitude: "+tabpane.getLongitude());
					
					    //Displays Output to Gui
					    output.setText("Longitude: "+tabpane.getLongitude()+"\n"+"Latitude: "+tabpane.getLatitude()+"\n"+results.displayOutPut());
					    table.setToTable(results);
					    tabpane.setLonError("");
					    tabpane.setLatError("");
					//                        submitLayout.getChildren().add(mainTab);
					    mainOutput.setContent(table.table());
					    mainTab.requestLayout();
					    mainTab.setVisible(true);
					//                        window.setScene(resultsScene);
					} catch (Exception e1) {
					    // TODO Auto-generated catch block
					    e1.printStackTrace();
					}
		    } else {
		        if (tabpane.getLongitude().isEmpty() || !isDouble(tabpane.getLongitude()) || Double.parseDouble(tabpane.getLongitude())>180 || Double.parseDouble(tabpane.getLongitude())<-180)
		        	tabpane.setLonError("Please enter a valid longitude value");
		        if (tabpane.getLatitude().isEmpty() || !isDouble(tabpane.getLatitude()) || Double.parseDouble(tabpane.getLatitude())>90 || Double.parseDouble(tabpane.getLatitude())<-90)
		        	tabpane.setLatError("Please enter a valid latitude value");
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
}
