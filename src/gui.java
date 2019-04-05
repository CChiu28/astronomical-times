import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
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


        //CENTER
        Label summary = new Label("The Astronomical Time calculator can be used to find the sunrise, sunset, \nand astronomical twilight and much more information at any location.");
        summary.setPadding(new Insets(10,10,10,10));
        BorderPane.setMargin(summary, new Insets(10,10,10,10));

        //BOTTOM
        Button enter = new Button("ENTER");
        enter.setPadding(new Insets(10,10,10,10));
        BorderPane.setMargin(enter, new Insets(10,10,10,10));
        enter.setOnAction(e -> window.setScene(displayScene));


        //DISPLAY SCENE

        VBox submitLayout = new VBox(10);

        //LONGITUDE TEXTBOX

        final TextField longitude = new TextField();
        longitude.setPromptText("Enter the Longitude : 0 to 90");
        longitude.setPrefColumnCount(5);
        longitude.getText();

        //LATTITUDE TEXTBOX

        final TextField latitude = new TextField();
        latitude.setPromptText("Enter the Latitude : 0 to 180");
        latitude.setPrefColumnCount(20);
        latitude.getText();

        //DATE TEXTBOX

        final TextField date = new TextField();
        date.setPromptText("Date");
        date.setPrefColumnCount(20);
        date.getText();

        //SUBMIT BUTTON

        final Button submit = new Button ("Submit!");
        GridPane.setConstraints(submit, 5, 18);

        //RESULTS SCENE

        VBox resultsLayout = new VBox(10);

        //RETURN TO DISPLAY SCENE BUTTON

        Button returnButton = new Button("BACK");
        returnButton.setOnAction(e -> window.setScene(displayScene));

        //OUTPUT TEXT

        final Text output = new Text();

        /*********************************************************/

        titleLayout.getChildren().addAll(title,summary,enter);
        titleLayout.setAlignment(Pos.CENTER);

        submitLayout.getChildren().addAll(longitude, latitude, date, submit);

        resultsLayout.getChildren().addAll(returnButton, output);
        resultsLayout.setAlignment(Pos.CENTER);

        titleScene = new Scene(titleLayout, 600,450);
        displayScene = new Scene(submitLayout, 600,450);
        resultsScene = new Scene(resultsLayout, 600,450);
        window.setTitle("Astronomical Times");
        window.setScene(titleScene);
        titleScene.getStylesheets().add("gStyle.css");



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
                        /* Changes the scene to resultScene.
                         * Takes the inputs from gui and converts into doubles.
                         * These values are then passed into the getData obj to connect to API.
                         * Results are mapped into a Data obj and getAll() is called.
                         * This needs to be reworked so results are outputed into
                         * the gui instead of console. The getters in Data will have to be used here.*/

                        window.setScene(resultsScene);

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