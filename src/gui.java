
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;


public class gui extends Application {

    Stage mainStage;
    Scene mainScene;

    public void start(Stage primaryStage) throws Exception {
    	// Set Stage
//        window = primaryStage;
//        window.setHeight(720);
//        window.setWidth(1280);
//        window.setTitle("Astronomical Times");
//
//        VBox titleLayout = new VBox(20);
//        TitleScene title = new TitleScene();
//
//        // Set the title scene
//        titleLayout.getChildren().add(title.Title(window));
//        titleLayout.setAlignment(Pos.CENTER);
//
//        titleScene = new Scene(titleLayout);
//        titleScene.getStylesheets().add("titleStyle.css");
//
//        window.setScene(titleScene);
//
//        window.show();
		mainStage = primaryStage;
		mainStage.setTitle("Astronomical Times");
		mainStage.setHeight(800);
		mainStage.setWidth(1280);

//		FXMLLoader loader = new FXMLLoader();
		try {
			mainScene = FXMLLoader.load(getClass().getResource("./MainLayout.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mainStage.setScene(mainScene);
		mainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
