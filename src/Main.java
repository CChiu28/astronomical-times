import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		gui.execute(args);
		String url = "wefwefwe";
		String date = "";
		getData data = new getData();
		
		
		
		try {
			data.sendGET(url,date);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error");
		} 
		
		
	}
}

