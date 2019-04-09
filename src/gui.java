import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class gui extends Application {

    Stage window;
    Scene titleScene, displayScene, resultsScene;


    public void start(Stage primaryStage) throws FileNotFoundException {

        window = primaryStage;
        

        //IMAGE Loader
        /*
        Image titleImage = new Image(new FileInputStream("assets/display_sun.jpg"));
        ImageView imageView = new ImageView(titleImage);
        // imageView.setX(50);
        // imageView.setY(25);
        imageView.setImage(titleImage);
        imageView.setFitHeight(600);
        imageView.setFitWidth(450);
        */

        // TITLE SCENE

        VBox titleLayout = new VBox(20);

        //TOP
        Label title = new Label("Astronomical Times");
        title.setPadding(new Insets(10,10,100,10));
        title.setFont(Font.font(36));
        BorderPane.setMargin(title, new Insets(10,10,200,10));
        title.setTextFill(Color.WHITE);


        //CENTER
        Label summary = new Label("The Astronomical Time calculator can be used to find the sunrise, sunset, \nand astronomical twilight and much more information at any location.");
        summary.setPadding(new Insets(10,10,10,10));
        BorderPane.setMargin(summary, new Insets(10,10,10,10));
        summary.setTextFill(Color.WHITE);

        //BOTTOM
        Button enter = new Button("Enter");
        enter.setPadding(new Insets(10,10,10,10));
        BorderPane.setMargin(enter, new Insets(10,10,10,10));
        enter.setOnAction(e -> window.setScene(displayScene));


        //DISPLAY SCENE

        VBox submitLayout = new VBox(10);
        
        // This sets up a tabpane for the location an coordinates input
        TabInput tabpane = new TabInput();

        //DATE TEXTBOX

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

        Button returnButton = new Button("Back");
        returnButton.setOnAction(e -> window.setScene(displayScene));

        //OUTPUT TEXT

        final Text output = new Text();
        output.setFill(Color.WHITE);

        /*********************************************************/

        titleLayout.getChildren().addAll(title,summary,enter);
        titleLayout.setAlignment(Pos.CENTER);

        // Adding the tabpane here will insert the tabs into the main layout
        submitLayout.getChildren().addAll(tabpane.tabpane(), date, submit);

        resultsLayout.getChildren().addAll(returnButton, output);
        resultsLayout.setAlignment(Pos.CENTER);

        titleScene = new Scene(titleLayout, 600,450);
        displayScene = new Scene(submitLayout, 600,450);
        resultsScene = new Scene(resultsLayout, 600,450);
        window.setTitle("Astronomical Times");
        window.setScene(titleScene);
        titleScene.getStylesheets().add("titleStyle.css");
        displayScene.getStylesheets().add("displayStyle.css");
        resultsScene.getStylesheets().add("resultsStyle.css");


        /* This is the event handler for the submit button.
         * Contains all the work when button is pressed.
         */

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                if (inputValidate(tabpane.longitude.getText(), tabpane.latitude.getText())) {

                    getData getdata = new getData(); // getData obj for API call

                    try {
                        /* Changes the scene to resultScene.
                         * Takes the inputs from gui and converts into doubles.
                         * These values are then passed into the getData obj to connect to API.
                         * Results are mapped into a Data obj and getAll() is called.
                         * This needs to be reworked so results are outputed into
                         * the gui instead of console. The getters in Data will have to be used here.*/

                        window.setScene(resultsScene);

                        Data results = getdata.sendGET(Double.parseDouble(tabpane.getLatitude()), Double.parseDouble(tabpane.getLongitude()), date.getText());
                        System.out.println("Latitude: "+tabpane.getLatitude());
                        System.out.println("Longitude: "+tabpane.getLongitude());

                        //Displays Output to Gui
                        output.setText("Longitude: "+tabpane.getLongitude()+"\n"+"Latitude: "+tabpane.getLatitude()+"\n"+results.displayOutPut());
                        tabpane.setLonError("");
                        tabpane.setLatError("");

                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
	                if (tabpane.getLongitude().isEmpty() || !isDouble(tabpane.getLongitude()) || Double.parseDouble(tabpane.getLongitude())>180 || Double.parseDouble(tabpane.getLongitude())<-180)
	                	tabpane.setLonError("Please enter a valid longitude value");
	                if (tabpane.latitude.getText().isEmpty() || !isDouble(tabpane.getLatitude()) || Double.parseDouble(tabpane.getLatitude())>90 || Double.parseDouble(tabpane.getLatitude())<-90)
	                	tabpane.setLatError("Please enter a valid latitude value");
                }
            }
        });

        window.show();
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
    
    public static void execute(String[] args) {
        launch(args);
    }
}