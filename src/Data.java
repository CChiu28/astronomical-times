//This class emulates the structure of the API's JSON format.

public class Data {
	// This is the first layer of the JSON structure: results. A getAll method is included to print all the data.
	Results results = new Results();
	void getAll() {
		System.out.println("Sunrise: "+results.getSunrise());
		System.out.println("Sunset: "+results.getSunset());
		System.out.println("Solar noon: "+results.getSolar_noon());
		System.out.println("Day length: "+results.getDay_length()+" hours");
		System.out.println("Civil twilight begins: "+results.getCivil_twilight_begin());
		System.out.println("Civil twilight ends: "+results.getCivil_twilight_end());
		System.out.println("Nautical twilight begins: "+results.getNautical_twilight_begin());
		System.out.println("Nautical twilight ends: "+results.getNautical_twilight_end());
		System.out.println("Astronomical twilight begins: "+results.getAstronomical_twilight_begin());
		System.out.println("Astronomical twilight ends: "+results.getAstronomical_twilight_end());
	}
}

// This is the second layer of the JSON structure, the actual data. Getters and Setters included.
// There shouldn't be any use for the Setters yet, if at all.
class Results {
	private String sunrise;
	private String sunset;
	private String solar_noon;
	private String day_length;
	private String civil_twilight_begin;
	private String civil_twilight_end;
	private String nautical_twilight_begin;
	private String nautical_twilight_end;
	private String astronomical_twilight_begin;
	private String astronomical_twilight_end;
	
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	public String getSolar_noon() {
		return solar_noon;
	}
	public void setSolar_noon(String solar_noon) {
		this.solar_noon = solar_noon;
	}
	public String getDay_length() {
		return day_length;
	}
	public void setDay_length(String day_length) {
		this.day_length = day_length;
	}
	public String getCivil_twilight_begin() {
		return civil_twilight_begin;
	}
	public void setCivil_twilight_begin(String civil_twilight_begin) {
		this.civil_twilight_begin = civil_twilight_begin;
	}
	public String getCivil_twilight_end() {
		return civil_twilight_end;
	}
	public void setCivil_twilight_end(String civil_twilight_end) {
		this.civil_twilight_end = civil_twilight_end;
	}
	public String getNautical_twilight_begin() {
		return nautical_twilight_begin;
	}
	public void setNautical_twilight_begin(String nautical_twilight_begin) {
		this.nautical_twilight_begin = nautical_twilight_begin;
	}
	public String getNautical_twilight_end() {
		return nautical_twilight_end;
	}
	public void setNautical_twilight_end(String nautical_twilight_end) {
		this.nautical_twilight_end = nautical_twilight_end;
	}
	public String getAstronomical_twilight_begin() {
		return astronomical_twilight_begin;
	}
	public void setAstronomical_twilight_begin(String astronomical_twilight_begin) {
		this.astronomical_twilight_begin = astronomical_twilight_begin;
	}
	public String getAstronomical_twilight_end() {
		return astronomical_twilight_end;
	}
	public void setAstronomical_twilight_end(String astronomical_twilight_end) {
		this.astronomical_twilight_end = astronomical_twilight_end;
	}
}