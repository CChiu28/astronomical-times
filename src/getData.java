import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

// This is google's JSON parser. It's included as a dependency through Maven so it can be used in this project.
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class getData {
	/** {@link #sendGET(double, double, String)}
	 * @param lat
	 * @param lng
	 * @param date
	 * @return results
	 * @throws Exception
	 * This method currently takes 3 parameters (will be changed) to take in latitude and longitude and optional date.
	 * The three parameters will be changed to take a Map object from {@link #geoCode(String)}). The coordinate values from the Map
	 * will be taken and passed into the url.
	 * Gson is used to parse the data. It takes a BufferedReader from {@link #connectAPI(String)} to connect to the API
	 * and maps it to a Data object. This Data object is returned.
	 */
    Data sendGET(double lat, double lng, String date) throws Exception {
    	// This string will concatenate lat, lng, and date to the url
        String url = "https://api.sunrise-sunset.org/json?lat="+lat+"&lng="+lng+"&date="+date;
        System.out.println("Sending GET request to "+url);
        Data results = (Data) new Gson().fromJson(connectAPI(url), Data.class); // Gson parses the incoming JSON data and maps it to a Data obj.
        return results;
    }
    
    /** {@link #geoCode(String)}
     * @param input
     * @return results
     * @throws Exception
     * This method takes input from the gui and geocodes it to retrieve the
     * accompanying latitude and longitude coordinates. A map with the accompanying
     * coordinates is returned.
     */
    Map<String, String> geoCode(String input) throws Exception {
    	/**
    	 * This parses the gui input and concatenates it to a String.
    	 * This is passed to {@link #connectAPI(String)} to connect to the API.
    	 */
    	StringBuffer url = new StringBuffer();
    	String[] address = input.split(" ");
    	url.append("https://nominatim.openstreetmap.org/search?format=json&addressdetails=1&q=");
    	if (address.length==0)
    		return null;
    	for (int i=0; i<address.length; i++) {
    		url.append(address[i]);
    		if (i<address.length-1)
    			url.append("+");
    	}
    	System.out.println(url.toString());
    	BufferedReader in = connectAPI(url.toString());
    	/*
    	 * After connecting to API, a StringBuffer is used to take in
    	 * the streamed data from the BufferedReader. This StringBuffer
    	 * will be parsed.
    	 */
    	String line;
    	StringBuffer res = new StringBuffer();
    	while ((line=in.readLine())!=null) {
    		res.append(line);
    	}
    	in.close();
    	System.out.println(res.toString());
    	/**
    	 * The StringBuffer is parsed via {@link #parseGeo(StringBuffer)} and
    	 * a JsonObject is returned. We only get the necessary elements we need from
    	 * the JsonObject which are the latitude and longitude coordinates.
    	 */
    	JsonObject jo = parseGeo(res);
    	String lon = jo.getAsJsonPrimitive("lon").getAsString();
    	String lat = jo.getAsJsonPrimitive("lat").getAsString();
    	String display = jo.getAsJsonPrimitive("display_name").getAsString();
    	System.out.println(lon);
    	System.out.println(lat);
    	System.out.println(display);
    	/**
    	 * A Map of <String,String> is created to take on the values from the JsonObject.
    	 * This map should be returned to {@link #sendGET}
    	 */
    	Map<String,String> result = new HashMap<String,String>();
    	result.put("lon", lon);
    	result.put("lat", lat);
    	result.put("displayname", display);
    	return result;
    }
    
    /** {@link #connectAPI(String)}
     * @param url
     * @return reader
     * @throws Exception
     * This method is used to connect to an API.
     * The parameter url is passed into an URL object which is opened through
     * a HttpURLConnection object.
     * A BufferedReader is returned with an open stream to the API.
     * It's private because it's not used outside this class.
     */
    private BufferedReader connectAPI(String url) throws Exception {
    	URL obj = new URL(url);
    	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
    	return reader;
    }
    
    /** {@link #parseGeo(StringBuffer)}
     * @param res
     * @return jo
     * This method is used to parse the data from the geocoding API.
     * It takes the streamed data from parser and organizes it in a
     * JsonArray. A JsonObject takes the data from the array and is returned.
     * It's private because it's not used outside this class.
     */
    private JsonObject parseGeo(StringBuffer res) {
    	JsonParser parser = new JsonParser();
    	JsonArray array = (JsonArray) parser.parse(res.toString());
    	JsonObject jo = new JsonObject();
    	if (array.size()>0) {
    		jo = (JsonObject) array.get(0);
    	}
    	return jo;
    }
}