// This class emulates the structure of the API's JSON format.
// The API's JSON data has a nested structure so this class has an inner class
// to reflect that.
// Gson should map the JSON data from the API to an object of this class
// through the sendGET() method in the getData class.
// In order to use this, call objName.results.getWhatever();

public class Data {
	// This is the first layer of the JSON structure: results. A getAll method is included to print all the data.
	private Results results = new Results();

	public Results res() {
		return this.results;
	}
	//Tester function to see results
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

	String displayOutPut(){

		String text = "Sunrise: "+results.getSunrise() +
		"\nSunset: "+results.getSunset() +
		"\nSolar noon: "+results.getSolar_noon() +
		"\nDay length: "+results.getDay_length()+" hours" +
		"\nCivil twilight begins: "+results.getCivil_twilight_begin() +
		"\nCivil twilight ends: "+results.getCivil_twilight_end() +
		"\nNautical twilight begins: "+results.getNautical_twilight_begin() +
		"\nNautical twilight ends: "+results.getNautical_twilight_end() +
		"\nAstronomical twilight begins: "+results.getAstronomical_twilight_begin() +
		"\nAstronomical twilight ends: "+results.getAstronomical_twilight_end();

		return text;
	}
}

// This is the second layer of the JSON structure, the actual data. Getters included.
//class Results {
//	private String sunrise;
//	private String sunset;
//	private String solar_noon;
//	private String day_length;
//	private String civil_twilight_begin;
//	private String civil_twilight_end;
//	private String nautical_twilight_begin;
//	private String nautical_twilight_end;
//	private String astronomical_twilight_begin;
//	private String astronomical_twilight_end;
//	
//	public String getSunrise() {
//		return sunrise;
//	}
//	public String getSunset() {
//		return sunset;
//	}
//	public String getSolar_noon() {
//		return solar_noon;
//	}
//	public String getDay_length() {
//		return day_length;
//	}
//	public String getCivil_twilight_begin() {
//		return civil_twilight_begin;
//	}
//	public String getCivil_twilight_end() {
//		return civil_twilight_end;
//	}
//	public String getNautical_twilight_begin() {
//		return nautical_twilight_begin;
//	}
//	public String getNautical_twilight_end() {
//		return nautical_twilight_end;
//	}
//	public String getAstronomical_twilight_begin() {
//		return astronomical_twilight_begin;
//	}
//	public String getAstronomical_twilight_end() {
//		return astronomical_twilight_end;
//	}
//}