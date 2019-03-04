import com.google.gson.*;

public class Main {
	public static void main(String[] args) {
//		This is just a test. This is all subject to change.
		getData time = new getData();
		try {
			Data results = time.sendGET();
			results.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}