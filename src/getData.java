import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;
import java.io.InputStreamReader;

public class getData {
//	This method returns a Response object. It takes in the url for the API as well as parameters
//	for latitude, longitude, and date. It will open a connection via HttpURLConnection.
//	The InputStreamReader will read the incoming data from the API. Gson will be used to parse
//	the API's incoming JSON data and it will be set to a new Response object which is then returned by this method.
    Response sendGET() throws Exception {
    	// This string will have to be updated to add string values for lat and long and date
        String url = "https://api.sunrise-sunset.org/json?lat=40.607009&lng=-74.000074"; // Example lat and long
        URL obj = new URL(url); //
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        InputStreamReader reader = new InputStreamReader(obj.openStream());
        System.out.println("Sending GET request to "+url);
        System.out.println("Response code: "+responseCode);
        Response results = (Response) new Gson().fromJson(reader, Response.class);
        return results;
    }
}