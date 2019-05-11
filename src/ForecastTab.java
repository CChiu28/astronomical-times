
import java.time.LocalDate;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

public class ForecastTab {
	Data info;
	getData getdata = new getData();
	String headerName = "";
	
	public void setCell(getData get, ObservableList<TitledPane> list, Data data, String location, String lat, String lng, LocalDate date, int check) {
		JFXListView<TitledPane> tmp = new JFXListView<TitledPane>();
		info = data;
		TitledPane header = setHeader(get);
		list.add(header);
		try {
			for (int i=0; i<5; i++) {
				list.add(getCast(info,date.toString()));
				date = date.plusDays(1);
				info = getdata.sendGET(lat, lng, location, date.toString(), check);
				System.out.println(date.toString());
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public TitledPane getCast(Data data, String date) {
		VBox box = new VBox(5);
		
		Label sunrise = new Label(data.res().getSunrise());
		Label sunset = new Label(data.res().getSunset());
		Label solNoon = new Label(data.res().getSolar_noon());
		Label dayLength = new Label(data.res().getDay_length()+" hours");
		Label civilBegin = new Label(data.res().getCivil_twilight_begin());
		Label nauBegin = new Label(data.res().getNautical_twilight_begin());
		Label astBegin = new Label(data.res().getAstronomical_twilight_begin());
		Label civilEnd = new Label(data.res().getCivil_twilight_end());
		Label nauEnd = new Label(data.res().getNautical_twilight_end());
		Label astEnd = new Label(data.res().getAstronomical_twilight_end());
		
		box.getChildren().addAll(sunrise,sunset,solNoon,dayLength,civilBegin,nauBegin,astBegin,civilEnd,nauEnd,astEnd);
		
		TitledPane node = new TitledPane(date, box);
		node.setCollapsible(false);
		
		return node;
	}
	
	private TitledPane setHeader(getData get) {
		VBox box = new VBox(5);
		
		Label sunrise = new Label("Sunrise");
		Label sunset = new Label("Sunset");
		Label solNoon = new Label("Solar Noon");
		Label dayLength = new Label("Day Length");
		Label civilBegin = new Label("Civil Twilight Begins");
		Label nauBegin = new Label("Nautical Twilight Begins");
		Label astBegin = new Label("Astronomical Twilight Begins");
		Label civilEnd = new Label("Civil Twilight Ends");
		Label nauEnd = new Label("Nautical Twilight Ends");
		Label astEnd = new Label("Astronomical Twilight Ends");
		
		box.getChildren().addAll(sunrise,sunset,solNoon,dayLength,civilBegin,nauBegin,astBegin,civilEnd,nauEnd,astEnd);
		
		TitledPane node = new TitledPane(get.getDisplayName(), box);
		node.setCollapsible(false);
		
		return node;
	}
}
