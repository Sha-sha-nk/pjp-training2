package com.shashank.incomeevaluator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="currency_usd_conversion")
public class CurrencyUSDConversion {
	@Id
	private int currency_id;
    private String currency;
    private Double conversion_factor;
    
	public CurrencyUSDConversion() {
		super();
	}
	public CurrencyUSDConversion(int currency_id,Double conversion_factor, String currency) {
		super();
		this.currency_id = currency_id;
		this.currency = currency;
		this.conversion_factor = conversion_factor;
	}
	public int getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(int currency_id) {
		this.currency_id = currency_id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getConversion_factor() {
		return conversion_factor;
	}
	public void setConversion_factor(Double conversion_factor) {
		this.conversion_factor = conversion_factor;
	}
	@Override
	public String toString() {
		return "CurrencyUSDConversion [currency_id=" + currency_id + ", currency=" + currency + ", conversion_factor="
				+ conversion_factor + "]";
	}
    
	
    
      
}
