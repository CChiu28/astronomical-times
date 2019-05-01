import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

/*
 * This class sets up the main results that displays the json data in the main results tab
 */
public class MainOutput {
	private TilePane layout;
	private TilePane layout2;
	private Label[] labels;
	private Label[] output = new Label[10];
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
				new Label("Day Length:"),
				new Label("Civil Twilight Begins:"),
				new Label("Civil Twilight Ends:"),
				new Label("Astronomical Twilight Begins:"),
				new Label("Astronomical Twilight Ends:"),
				new Label("Nautical Twilight Begins:"),
				new Label("Nautical Twilight Ends:")
		};

		for (Label label : labels) {
			label.setTextFill(Color.BEIGE);
			label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		}
		
		ObservableList list = layout.getChildren();
		list.addAll(labels);
		
		return layout;
	}
	
	public TilePane Output(Results res) {
		layout2 = new TilePane();
		layout2.setOrientation(Orientation.VERTICAL);
		layout2.setPrefRows(10);
		layout2.setTileAlignment(Pos.CENTER_RIGHT);
		layout2.setVgap(5);
		
		output[0].setText(res.getSunrise());
		output[1].setText(res.getSunset());
		output[2].setText(res.getSolar_noon());
		output[3].setText(res.getDay_length()+" hours");
		output[4].setText(res.getCivil_twilight_begin());
		output[5].setText(res.getCivil_twilight_end());
		output[6].setText(res.getAstronomical_twilight_begin());
		output[7].setText(res.getAstronomical_twilight_end());
		output[8].setText(res.getNautical_twilight_begin());
		output[9].setText(res.getNautical_twilight_end());
		
		for(Label label : output) {
			label.setStyle("-fx-font-size: 18px");
			label.setTextFill(Color.BISQUE);
		}
		
		ObservableList list = layout2.getChildren();
		list.addAll(output);
		
		return layout2;
	}
	
	public void clean() {
		for (int i=0; i<output.length; i++)
			output[i] = new Label("");
			
	}
}
