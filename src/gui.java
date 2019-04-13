import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
//import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.paint.*;
import javafx.scene.layout.*;


public class gui extends Application {

    Stage window;
    Scene titleScene;

    public void start(Stage primaryStage) throws FileNotFoundException {

        window = primaryStage;
        window.setHeight(720);
        window.setWidth(1280);
        window.setTitle("Astronomical Times");

        VBox titleLayout = new VBox(20);
        TitleScene title = new TitleScene();
        
        titleLayout.getChildren().addAll(title.Title(window));
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