// This class emulates the structure of the API's JSON format.
// The API's JSON data has a nested structure so this class has an inner class
// to reflect that.
// Gson should map the JSON data from the API to an object of this class
// through the sendGET() method in the getData class.

public class Data {
	// This is the first layer of the JSON structure: results. It contains all the
	// vaiables that should map the json data
	private Results results = new Results();

	public Results res() {
		return this.results;
	}
	
	public void setRes(Results res) {
		this.results = res;
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