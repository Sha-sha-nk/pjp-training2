package com.shashank.incomeevaluator.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "location_income")
public class LocationIncome{
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="location_id",updatable=false,nullable=false)
	private int location_id;
	private String city;
	private String country;
	private String gender;
	private String currency;
	private double average_income;
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = (city.length()!=0)?city:null;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = (country.length()!=0)?country:null;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = (gender.length()!=0)?gender:null;;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = (currency.length()!=0)?currency:null;;
	}
	public double getAverage_income() {
		return average_income;
	}
	public void setAverage_income(double average_income) {
		this.average_income = average_income;
	}
	@Override
	public String toString() {
		return "LocationIncome [location_id=" + location_id + ", city=" + city + ", country=" + country + ", gender="
				+ gender + ", currency=" + currency + ", average_income=" + average_income + "]";
	}
	
	
}
