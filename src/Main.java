
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	Stage mainStage;
	Parent mainScene;
	public static void main(String[] args) {
//		gui.main(args);
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		mainStage = stage;
		mainStage.setTitle("Astronomical Times");

//		FXMLLoader loader = new FXMLLoader();
		mainScene = FXMLLoader.load(getClass().getResource("./MainLayout.fxml"));

		mainStage.setScene(new Scene(mainScene, 1400, 800));
		mainStage.show();
	}
}