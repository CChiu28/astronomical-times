package com.astronomicaltimes;

/*
 * This class contains the json structure of the sunrisesunset API.
 * It includes all the variables needed to map each json property.
 */
public class Results {
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
	
	public void setVal(String s, String set) {
		switch(s) {
		case "sunrise":
			this.sunrise = set; break;
		case "sunset":
			this.sunset = set; break;
		case "solarNoon":
			this.solar_noon = set; break;
		case "dayLength":
			this.day_length = set; break;
		case "civilBTime":
			this.civil_twilight_begin = set; break;
		case "civilETime":
			this.civil_twilight_end = set; break;
		case "nauBTime":
			this.nautical_twilight_begin = set; break;
		case "nauETime":
			this.nautical_twilight_end = set; break;
		case "astBTime":
			this.astronomical_twilight_begin = set; break;
		case "astETime":
			this.astronomical_twilight_end = set; break;
		}
	}
	
	public void copy(Results res) {
		this.sunrise = res.getSunrise();
		this.sunset = res.getSunset();
		this.solar_noon = res.getSolar_noon();
		this.day_length = res.getDay_length();
		this.civil_twilight_begin = res.getCivil_twilight_begin();
		this.civil_twilight_end = res.getCivil_twilight_end();
		this.nautical_twilight_begin = res.getNautical_twilight_begin();
		this.nautical_twilight_end = res.getNautical_twilight_end();
		this.astronomical_twilight_begin = res.getAstronomical_twilight_begin();
		this.astronomical_twilight_end = res.getAstronomical_twilight_end();
	}
	
	public String getSunrise() {
		return sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public String getSolar_noon() {
		return solar_noon;
	}
	public String getDay_length() {
		return day_length;
	}
	public String getCivil_twilight_begin() {
		return civil_twilight_begin;
	}
	public String getCivil_twilight_end() {
		return civil_twilight_end;
	}
	public String getNautical_twilight_begin() {
		return nautical_twilight_begin;
	}
	public String getNautical_twilight_end() {
		return nautical_twilight_end;
	}
	public String getAstronomical_twilight_begin() {
		return astronomical_twilight_begin;
	}
	public String getAstronomical_twilight_end() {
		return astronomical_twilight_end;
	}
}