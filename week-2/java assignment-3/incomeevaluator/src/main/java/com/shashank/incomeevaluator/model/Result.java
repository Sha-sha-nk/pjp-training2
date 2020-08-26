package com.shashank.incomeevaluator.model;

public class Result {
    private String city_country;
    private String gender;
    private double usdAverageIncome;
    
	public Result(String city_country, String gender, double usdAverageIncome) {
		super();
		this.city_country = city_country;
		this.gender = gender;
		this.usdAverageIncome = usdAverageIncome;
	}

	public String getCity_country() {
		return city_country;
	}

	public String getGender() {
		return gender;
	}

	public double getUsdAverageIncome() {
		return usdAverageIncome;
	}

	@Override
	public String toString() {
		return "Result [city_country=" + city_country + ", gender=" + gender + ", usdAverageIncome=" + usdAverageIncome
				+ "]";
	}
    
    
    
}
