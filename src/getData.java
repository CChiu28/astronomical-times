import java.net.HttpURLConnection;
import java.net.URL;
// This is google's JSON parser. It's included as a dependency through Maven so it can be used in this project.
import com.google.gson.*;
import java.io.InputStreamReader;

public class getData {
//	This method returns a Data object as defined in Data.java. It takes in the url for the API as well as parameters
//	for latitude, longitude, and date. It will open a connection via HttpURLConnection.
//	The InputStreamReader will read the incoming data from the API. Gson will be used to parse
//	the API's incoming JSON formatted data and it will be mapped to a new Data object. That Data object is returned.
    Data sendGET(double lat, double lng, String date) throws Exception {
    	// This string will concatenate lat, lng, and date to the url
        String url = "https://api.sunrise-sunset.org/json?lat="+lat+"&lng="+lng+"&date="+date;
        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection(); // Cast URL obj to HttpUrlConnection
//        con.setRequestMethod("GET"); // Set request to GET
        InputStreamReader reader = new InputStreamReader(obj.openStream()); // Open a readable stream to the API
        System.out.println("Sending GET request to "+url);
//        System.out.println("Data code: "+con.getResponseCode());
        Data results = (Data) new Gson().fromJson(reader, Data.class); // Gson parses the incoming JSON data and maps it to a Data obj.
        return results;
    }
}