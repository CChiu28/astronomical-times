import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Table {
	private TableView<String> view;
	Data data;
	public TableView<String> table() {
		view = new TableView<String>();
		data = new Data();
		view.setEditable(false);
		TableColumn<String,Data> firstCol = new TableColumn<>("Times");
		TableColumn<String,Data> secCol = new TableColumn<>("Data");
		firstCol.setCellValueFactory(new PropertyValueFactory<String,Data>("results"));
		
		view.getColumns().add(firstCol);
		view.getColumns().add(secCol);
		
		view.getItems().add(data.results.getAstronomical_twilight_begin());
		return view;
	}
}
