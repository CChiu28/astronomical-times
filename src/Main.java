import com.google.gson.*;

public class Main {
	public static void main(String[] args) {
//		This is just a test. This is all subject to change.
		double lat = 40.607009;
		double lng = -74.000074;
		String date = "today";
		getData time = new getData();
		try {
			Data results = time.sendGET(lat,lng,date);
			results.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			//DOMS TEST

		}
	}
}