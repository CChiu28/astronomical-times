
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// This is google's JSON parser. It's included as a dependency through Maven so it can be used in this project.
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class getData {
	private final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssz";
	private Data results = new Data();
	private Map<String, String> result = new HashMap<String, String>();
	private String displayName = "";
	private final Charset charset = StandardCharsets.UTF_8;
	
	/** {@link #sendGET(String, String, String)}
	 * @param lat
	 * @param lng
	 * @param date
	 * @return results
	 * @throws Exception
	 * This method currently takes 3 parameters to take in latitude and longitude and a date.
	 * Gson is used to parse the data. It takes a BufferedReader from {@link #connectAPI(String)} to connect to the API
	 * and maps it to a Data object. This Data object is returned.
	 */
    Data sendGET(String lat, String lng, String loc, String date, int check) throws Exception {
    	// This string will concatenate lat, lng, and date to the url
    	String url = "";
    	Map<String, String> locate = new HashMap<String,String>();
    	if (check==1)
    		url = "https://api.sunrise-sunset.org/json?lat="+lat+"&lng="+lng+"&date="+date+"&formatted=0";
    	else {
    		locate = geoCode(loc);
    		url = "https://api.sunrise-sunset.org/json?lat="+locate.get("lat")+"&lng="+locate.get("lon")+"&date="+date+"&formatted=0";
    	}
        System.out.println("Sending GET request to "+url);
        Data original = (Data) new Gson().fromJson(connectAPI(url), Data.class); // Gson parses the incoming JSON data and maps it to a Data obj.
        if (check==1)
        	results.setRes(setTZ(getTZ(lat,lng), original));
        else results.setRes(setTZ(getTZ(locate.get("lat"),locate.get("lon")), original));
        return results;
    }
    /** {@link #sendGET(String, String)}
     * @param location
     * @param date
     * @return results
     * @throws Exception
     * This method takes two Strings, one for location and one for Date. The location is used to get coordinates via the
     * {@link #geoCode(String)} method and is passed to a Map. The coordinates are used to connect to the API to receive the times.
     */
//    Data sendGET(String location, String date) throws Exception {
//    	Map<String,String> locate = geoCode(location);
//		String url = "https://api.sunrise-sunset.org/json?lat="+locate.get("lat")+"&lng="+locate.get("lon")+"&date="+date+"&formatted=0";
//		System.out.println("Sending GET request to "+url);
//		Data original = (Data) new Gson().fromJson(connectAPI(url), Data.class); // Gson parses the incoming JSON data and maps it to a Data obj.
//		results.setRes(setTZ(getTZ(locate.get("lat"),locate.get("lon")), original));
//		return results;
//    }
    
    public Map<String,String> getLocationName() {
//    	String locName = this.result.get("displayname");
    	return this.result;
    }
    
    /** {@link #geoCode(String)}
     * @param input
     * @return results
     * @throws Exception
     * This method takes input from the gui and geocodes it to retrieve the
     * accompanying latitude and longitude coordinates. A map with the accompanying
     * coordinates is returned.
     */
    private Map<String, String> geoCode(String input) throws Exception {
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
    	System.out.println("Getting geo-coordinates from "+url.toString());
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
    	/**
    	 * The StringBuffer is parsed via {@link #parseGeo(StringBuffer)} and
    	 * a JsonObject is returned. We only get the necessary elements we need from
    	 * the JsonObject which are the latitude and longitude coordinates.
    	 */
    	JsonObject jo = parseGeo(res);
//    	String lon = jo.getAsJsonPrimitive("lon").getAsString();
//    	String lat = jo.getAsJsonPrimitive("lat").getAsString();
    	displayName = jo.getAsJsonPrimitive("display_name").getAsString();
    	/**
    	 * A Map of <String,String> is created to take on the values from the JsonObject.
    	 * This map should be returned to {@link #sendGET}
    	 */
//    	result = new HashMap<String,String>();
    	result.put("lon", jo.getAsJsonPrimitive("lon").getAsString());
    	result.put("lat", jo.getAsJsonPrimitive("lat").getAsString());
    	result.put("displayname", displayName);
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
    	BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
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
    
    /** {@link #getTZ(String, String)}
     * @param lat
     * @param lon
     * @return String
     * @throws Exception
     * This method takes the lat and lon coordinates and connects to an API that will return the
     * timezone of the respective coordinates.
     */
    private String getTZ(String lat, String lon) {
    	String tz = "UTC";
    	try {
	    	URL url = new URL("http://api.geonames.org/timezoneJSON?lat="+lat+"&lng="+lon+"&username=chchiu1028");
	    	System.out.println("Getting timezones from "+url.toString());
	    	BufferedReader reader = connectAPI(url.toString());
	    	String line;
	    	StringBuffer res = new StringBuffer();
	    	res.append("[");
	    	while ((line=reader.readLine())!=null) {
	    		res.append(line);
	    	}
	    	res.append("]");
	    	reader.close();
	    	JsonObject jo = parseGeo(res);
	    	tz = jo.getAsJsonPrimitive("timezoneId").getAsString();
    	} catch (Exception e ) {
    		System.out.println("No timezones available");
    	}
    	return tz;
    }
    
    /** {@link #setTZ(String, Data)
     * @param tz
     * @param data
     * @return Data
     * @throws Exception
     * This method takes the Data obj and the timezone and formats the time to its correct timezone.
     * The new times are passed to a new Data obj that is returned.
     */
    private Results setTZ(String tz, Data data) throws Exception {
    	Results res = data.res();
    	// Parse the Strings from our results to LocalDateTime
    	DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
    	
    	LocalDateTime sunrise = LocalDateTime.parse(res.getSunrise(), format);
    	LocalDateTime sunset = LocalDateTime.parse(res.getSunset(), format);
    	LocalDateTime solarnoon = LocalDateTime.parse(res.getSolar_noon(), format);
    	LocalDateTime civiltwib = LocalDateTime.parse(res.getCivil_twilight_begin(), format);
    	LocalDateTime civiltwie = LocalDateTime.parse(res.getCivil_twilight_end(), format);
    	LocalDateTime nautwib = LocalDateTime.parse(res.getNautical_twilight_begin(), format);
    	LocalDateTime nautwie = LocalDateTime.parse(res.getNautical_twilight_end(), format);
    	LocalDateTime asttwib = LocalDateTime.parse(res.getAstronomical_twilight_begin(), format);
    	LocalDateTime asttwie = LocalDateTime.parse(res.getAstronomical_twilight_end(), format);

    	// Set origin timezone which is UTC to the LocalDateTime and pass to ZonedDateTime
    	ZoneId utc = ZoneId.of("UTC");
    	ZonedDateTime fromsunrise = sunrise.atZone(utc);
    	ZonedDateTime fromsunset = sunset.atZone(utc);
    	ZonedDateTime fromsolarnoon = solarnoon.atZone(utc);
    	ZonedDateTime fromciviltwib = civiltwib.atZone(utc);
    	ZonedDateTime fromciviltwie = civiltwie.atZone(utc);
    	ZonedDateTime fromnautwib = nautwib.atZone(utc);
    	ZonedDateTime fromnautwie = nautwie.atZone(utc);
    	ZonedDateTime fromasttwib = asttwib.atZone(utc);
    	ZonedDateTime fromasttwie = asttwie.atZone(utc);
    	
    	// Set new timezone with the String parameter that was passed here
    	// and set DateTimeFormatter to parse and format the time to look simpler
    	ZoneId zone = ZoneId.of(tz);
    	format = DateTimeFormatter.ofPattern("hh:mm:ss a z");

    	// Formats the ZonedDateTimes to a string using the DateTimeFormatter's pattern and sets it back to the Results obj
    	res.setSunrise(format.format(fromsunrise.withZoneSameInstant(zone)));
    	res.setSunset(format.format(fromsunset.withZoneSameInstant(zone)));
    	res.setSolar_noon(format.format(fromsolarnoon.withZoneSameInstant(zone)));
    	res.setCivil_twilight_begin(format.format(fromciviltwib.withZoneSameInstant(zone)));
    	res.setCivil_twilight_end(format.format(fromciviltwie.withZoneSameInstant(zone)));
    	res.setNautical_twilight_begin(format.format(fromnautwib.withZoneSameInstant(zone)));
    	res.setNautical_twilight_end(format.format(fromnautwie.withZoneSameInstant(zone)));
    	res.setAstronomical_twilight_begin(format.format(fromasttwib.withZoneSameInstant(zone)));
    	res.setAstronomical_twilight_end(format.format(fromasttwie.withZoneSameInstant(zone)));
    	
    	// Format the day length from seconds to hours and round decimals to 2 places
    	Double dayLength = Double.parseDouble(res.getDay_length())/3600;
    	DecimalFormat df = new DecimalFormat("##.##");
    	res.setDay_length(df.format(dayLength));
    	
    	return res;
    }
    
    public String getDisplayName() {
    	return displayName;
    }
    
    public String getLat() {
    	return result.get("lat");
    }
    
    public String getLng() {
    	return result.get("lon");
    }
}