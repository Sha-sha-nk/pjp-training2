package com.shashank.feecalculator.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "client_transaction")
public class ClientTransaction{
    
	@Id
	@Column(name="external_transaction_id",updatable=false,nullable=false)
	private String  external_transaction_id;
	private String client_id;
	private String security_id;
	private String transaction_type;
	private Date transaction_date;
	private double market_value;
	private String priority_flag;
	
	public String getExternal_transaction_id() {
		return external_transaction_id;
	}
	public void setExternal_transaction_id(String external_transaction_id) {
		this.external_transaction_id = external_transaction_id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getSecurity_id() {
		return security_id;
	}
	public void setSecurity_id(String security_id) {
		this.security_id = security_id;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public Date getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	public double getMarket_value() {
		return market_value;
	}
	public void setMarket_value(double market_value) {
		this.market_value = market_value;
	}
	public String getPriority_flag() {
		return priority_flag;
	}
	public void setPriority_flag(String data) {
		this.priority_flag = data;
	}
	@Override
	public String toString() {
		return "client_transaction [external_transaction_id=" + external_transaction_id + ", client_id=" + client_id
				+ ", security_id=" + security_id + ", transaction_type=" + transaction_type + ", transaction_date="
				+ transaction_date + ", market_value=" + market_value + ", priority_flag=" + priority_flag + "]";
	}
	
	
}
