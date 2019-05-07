import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;


public class gui extends Application {

    Stage window;
    Scene titleScene;

    public void start(Stage primaryStage) throws FileNotFoundException {
    	// Set Stage
        window = primaryStage;
        window.setHeight(720);
        window.setWidth(1280);
        window.setTitle("Astronomical Times");

        VBox titleLayout = new VBox(20);
        TitleScene title = new TitleScene();
        
        // Set the title scene
        titleLayout.getChildren().add(title.Title(window));
        titleLayout.setAlignment(Pos.CENTER);

        titleScene = new Scene(titleLayout);
        titleScene.getStylesheets().add("titleStyle.css");

        window.setScene(titleScene);

        window.show();
    }

    
    public static void execute(String[] args) {
        launch(args);
    }
}