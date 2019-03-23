import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class gui extends Application {

  
  public void start(Stage stage) {
    Group root = new Group();
    Scene scene = new Scene(root, 1000, 800);
    stage.setScene(scene);
    stage.setTitle("Sun Tracker");

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(5);
    grid.setHgap(5);

    scene.setRoot(grid);
    
    int lg;
    int lt;
    int dt;
    lg = 36;
    lt = 88;
    dt = 140;
    
    Label lgtd = new Label("*Longitude:");
    Label lttd = new Label("*Latitude:");
    Label dte = new Label("Date:");
    lgtd.setTranslateY(lg);
    lttd.setTranslateY(lt);
    dte.setTranslateY(dt);

    final TextField longitude = new TextField();
    longitude.setPromptText("Enter the Longitude");
    longitude.setPrefColumnCount(20);
    longitude.getText();
    GridPane.setConstraints(longitude, 3, 3);
    grid.getChildren().add(longitude);
    grid.getChildren().add(lgtd);
    
    final TextField latitude = new TextField();
    latitude.setPromptText("Enter the Latitude");
    latitude.setPrefColumnCount(20);
    latitude.getText();
    GridPane.setConstraints(latitude, 3, 8);
    grid.getChildren().add(latitude);
    grid.getChildren().add(lttd);
    
    final TextField date = new TextField();
    date.setPromptText("Enter the Date");
    date.setPrefColumnCount(20);
    date.getText();
    GridPane.setConstraints(date, 3, 13);
    grid.getChildren().add(date);
    grid.getChildren().add(dte);
    
    final Button submit = new Button ("Submit!");
    stage.setScene(scene);
    GridPane.setConstraints(submit, 5, 18);
    grid.getChildren().add(submit);

    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
