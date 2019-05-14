package com.astronomicaltimes;
// This class emulates the structure of the API's JSON format.
// The API's JSON data has a nested structure so this class has an inner class
// to reflect that:
//	{
//	    "results":
//	    {
//	      "sunrise":"2015-05-21T05:05:35+00:00",
//	      "sunset":"2015-05-21T19:22:59+00:00",
//	      "solar_noon":"2015-05-21T12:14:17+00:00",
//	      "day_length":51444,
//	      "civil_twilight_begin":"2015-05-21T04:36:17+00:00",
//	      "civil_twilight_end":"2015-05-21T19:52:17+00:00",
//	      "nautical_twilight_begin":"2015-05-21T04:00:13+00:00",
//	      "nautical_twilight_end":"2015-05-21T20:28:21+00:00",
//	      "astronomical_twilight_begin":"2015-05-21T03:20:49+00:00",
//	      "astronomical_twilight_end":"2015-05-21T21:07:45+00:00"
//	    },
//	     "status":"OK"
//	}
// Gson should map the JSON data from the API to an object of this class
// through the sendGET() method in the getData class.

public class Data {
	// This is the first layer of the JSON structure: results. It contains all the
	// vaiables that should map the json data
	private Results results = new Results();

	public Results getRes() {
		return this.results;
	}

	public void copy(Results res) {
		this.results = res;
	}
}