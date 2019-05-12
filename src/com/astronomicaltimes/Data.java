package com.astronomicaltimes;
// This class emulates the structure of the API's JSON format.
// The API's JSON data has a nested structure so this class has an inner class
// to reflect that.
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