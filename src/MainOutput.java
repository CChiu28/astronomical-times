import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainOutput {
	TilePane layout;
	TilePane layout2;
	Label[] labels;
	Label[] output;
	public TilePane Labels() {
		layout = new TilePane();
		layout.setOrientation(Orientation.VERTICAL);
		layout.setPrefRows(10);
		layout.setTileAlignment(Pos.CENTER_RIGHT);
		layout.setVgap(5);
		
		labels = new Label[] {
				new Label("Sunrise:"),
				new Label("Sunset:"),
				new Label("Solar Noon:"),
				new Label("Day Length"),
				new Label("Civil Twilight Begins:"),
				new Label("Civil Twilight Ends:"),
				new Label("Astronomical Twilight Begins:"),
				new Label("Astronomical Twilight Ends:"),
				new Label("Nautical Twilight Begins:"),
				new Label("Nautical Twilight Ends:")
		};
		for (int i=0; i<labels.length; i++) {
			labels[i].setTextFill(Color.ALICEBLUE);
			labels[i].setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		}
		
		ObservableList list = layout.getChildren();
		list.addAll(labels);
		
		return layout;
	}
	
	public TilePane Output(Results res) {
		layout2 = new TilePane();
		layout2.setOrientation(Orientation.VERTICAL);
		layout2.setPrefRows(10);
		layout2.setTileAlignment(Pos.CENTER);
		layout2.setVgap(5);
		
		output = new Label[] {
				new Label(res.getSunrise()),
				new Label(res.getSunset()),
				new Label(res.getSolar_noon()),
				new Label(res.getDay_length()+" hours"),
				new Label(res.getCivil_twilight_begin()),
				new Label(res.getCivil_twilight_end()),
				new Label(res.getAstronomical_twilight_begin()),
				new Label(res.getAstronomical_twilight_end()),
				new Label(res.getNautical_twilight_begin()),
				new Label(res.getNautical_twilight_end())
		};
		for (int i=0; i<output.length; i++) {
			output[i].setStyle("-fx-font-size: 18px");
			output[i].setTextFill(Color.GREEN);
		}
		
		ObservableList list = layout2.getChildren();
		list.addAll(output);
		
		return layout2;
	}
}
