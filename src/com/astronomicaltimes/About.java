package com.astronomicaltimes;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/*
 * Credits tab
 */
public class About {
	private Label about;
	private VBox aboutTab;
	private Label link1;
	private Label link2;
	private Label link3;
	private Label link4;
	private Label link5;
	// Returns a VBox pane to MainScene
	public VBox about() {
		aboutTab = new VBox(20);
		aboutTab.setAlignment(Pos.TOP_CENTER);
		about = new Label("All data sourced from:");
		about.setStyle("-fx-font-weight: bold");
		link1 = new Label("https://sunrise-sunset.org/api");
		link2 = new Label("http://nominatim.org/release-docs/develop/api/Search/");
		link3 = new Label("http://www.geonames.org/export/web-services.html#timezone");
		link4 = new Label("Gson - Json parser by Google");
		link5 = new Label("JFoenix - Java component library");
		
		about.setTextFill(Color.WHITE);
		link1.setTextFill(Color.FIREBRICK);
		link2.setTextFill(Color.FIREBRICK);
		link3.setTextFill(Color.FIREBRICK);
		link4.setTextFill(Color.FIREBRICK);
		link5.setTextFill(Color.FIREBRICK);
		
		aboutTab.getChildren().addAll(about,link1,link2, link3, link4, link5);
		return aboutTab;
	}
}
