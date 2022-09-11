package model;

import java.util.Date;

import com.google.gson.Gson;

public class Catch extends JsonModel{
	private String userId; 
	private String tempr; 
	private String humid; 
	private String baro; 
	private String lat; 
	private String lon; 
	private String city; 
	private String species; 
	private String length1; 
	private String width;
	private String weight;
	private String date1; 
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getTemp() {
		return tempr;
	}
	public void setTemp(String tempr) {
		this.tempr = tempr;
	}
	
	public String getHumid() {
		return humid;
	}
	public void setHumid(String humid) {
		this.humid = humid;
	}
	
	public String getBaro() {
		return baro;
	}
	public void setBaro(String baro) {
		this.baro = baro;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getLength() {
		return length1;
	}
	public void setLength(String length) {
		this.length1 = length;
	}
	
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getDate() {
		return date1;
	}
	public void setDate(String date) {
		this.date1 = date;
	} 
	
}
