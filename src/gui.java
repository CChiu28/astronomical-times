import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

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
    GridPane.setConstraints(longitude, 5, 3);
    grid.getChildren().add(longitude);
    grid.getChildren().add(lgtd);
    
    final TextField latitude = new TextField();
    latitude.setPromptText("Enter the Latitude");
    latitude.setPrefColumnCount(20);
    latitude.getText();
    GridPane.setConstraints(latitude, 5, 8);
    grid.getChildren().add(latitude);
    grid.getChildren().add(lttd);
    
    final TextField date = new TextField();
    date.setPromptText("Enter the Date");
    date.setPrefColumnCount(20);
    date.getText();
    GridPane.setConstraints(date, 5, 13);
    grid.getChildren().add(date);
    grid.getChildren().add(dte);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>() {
    	@Override
    	public void handle(ActionEvent e) {
    		if (latitude.getText()!=null&&longitude.getText()!=null) {
    			getData data = new getData();
    			try {
					Data results = data.sendGET(Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()), date.getText());
					results.getAll();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	}
    });
    GridPane.setConstraints(submit, 5, 18);
    grid.getChildren().add(submit);
    
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
