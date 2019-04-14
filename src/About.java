import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class About {
	private Label about;
	private VBox aboutTab;
	Label link1;
	Label link2;
	public VBox about() {
		aboutTab = new VBox(20);
		aboutTab.setAlignment(Pos.TOP_CENTER);
		about = new Label("All data sourced from:");
		link1 = new Label("https://sunrise-sunset.org/api");
		link2 = new Label("http://nominatim.org/release-docs/develop/api/Search/");
		
		about.setTextFill(Color.WHITE);
		link1.setTextFill(Color.ANTIQUEWHITE);
		link2.setTextFill(Color.ANTIQUEWHITE);

		
		aboutTab.getChildren().addAll(about,link1,link2);
		return aboutTab;
	}
}
