import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;


public class gui extends Application {

  public void start(Stage window) {
    Group root = new Group();
    Scene scene = new Scene(root, 1000, 800);
    window.setScene(scene);
    window.setTitle("Sun Tracker");

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(5);
    grid.setHgap(5);

    scene.setRoot(grid);

    Label lgtd = new Label("*Longitude:");
    Label lttd = new Label("*Latitude:");
    Label dte = new Label("Date:");

    lgtd.setTranslateY(36);
    lttd.setTranslateY(88);
    dte.setTranslateY(140);

    //Textbox input for Longitude

    final TextField longitude = new TextField();
    longitude.setPromptText("Enter the Longitude : 0 to 90");
    longitude.setPrefColumnCount(20);
    longitude.getText();
    GridPane.setConstraints(longitude, 3, 3);
    grid.getChildren().add(longitude);
    grid.getChildren().add(lgtd);

    //Textbox input of Latitude

    final TextField latitude = new TextField();
    latitude.setPromptText("Enter the Latitude : 0 to 180");
    latitude.setPrefColumnCount(20);
    latitude.getText();
    GridPane.setConstraints(latitude, 3, 8);
    grid.getChildren().add(latitude);
    grid.getChildren().add(lttd);

    //Textbox input for date

    final TextField date = new TextField();
    date.setPromptText("Enter the Date");
    date.setPrefColumnCount(20);
    date.getText();
    GridPane.setConstraints(date, 3, 13);
    grid.getChildren().add(date);
    grid.getChildren().add(dte);

    //Button to Submit input

    final Button submit = new Button ("Submit!");
    GridPane.setConstraints(submit, 5, 18);
    grid.getChildren().add(submit);

  //Text for output

    final Text output = new Text();
    GridPane.setConstraints(output, 3, 19);
    grid.getChildren().add(output);
    output.setTranslateY(20);
    
    /* This is the event handler for the submit button.
     * Contains all the work when button is pressed.
     */

    submit.setOnAction(new EventHandler<ActionEvent>() {
    	@Override
    	public void handle(ActionEvent e) {

    		// If condition checks the input fields for any values. Only lat and lng are required.
    		// May need to be reworked for better input validation (ie. numbers only, etc)

    		if (latitude.getText()!= null && longitude.getText() !=null) {

    			getData getdata = new getData(); // getData obj for API call

    			try {
    				/* Takes the inputs from gui and converts into doubles.
    				 * These values are then passed into the getData obj to connect to API.
    				 * Results are mapped into a Data obj and getAll() is called.
    				 * This needs to be reworked so results are outputed into
    				 * the gui instead of console. The getters in Data will have to be used here.
    				 */

					Data results = getdata.sendGET(Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()), date.getText());
					System.out.println("Latitude: "+latitude.getText());
					System.out.println("Longitude: "+longitude.getText());

					//Displays Output to Gui
					output.setText("Longitude: "+longitude.getText()+"\n"+"Latitude: "+latitude.getText()+"\n"+results.displayOutPut());

					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	}
    });
    window.show();
  }

  public static void execute(String[] args) {
    launch(args);
  }
}
