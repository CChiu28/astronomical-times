import com.google.gson.*;

public class Main {
	public static void main(String[] args) {
		getData time = new getData();
		try {
			Response results = time.sendGET();
			results.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}