package com.shashank.feecalculator.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_report")
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="report_id",updatable=false,nullable=false)
	private int report_id ;
	private String client_id;
	private String transaction_type;
	private Date transaction_date;
	private String priority_flag;
	private int processing_fee;
	
	public Report() {
		super();
	}
	
	public Report(String client_id, String transaction_type, Date transaction_date, String priority_flag,
			int processing_fee) {
		super();
		this.client_id = client_id;
		this.transaction_type = transaction_type;
		this.transaction_date = transaction_date;
		this.priority_flag = priority_flag;
		this.processing_fee = processing_fee;
	}

	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
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
	public String getPriority_flag() {
		return priority_flag;
	}
	public void setPriority_flag(String priority_flag) {
		this.priority_flag = priority_flag;
	}
	public int getProcessing_fee() {
		return processing_fee;
	}
	public void setProcessing_fee(int processing_fee) {
		this.processing_fee = processing_fee;
	}
	@Override
	public String toString() {
		return "Report [report_id=" + report_id + ", client_id=" + client_id + ", transaction_type=" + transaction_type
				+ ", transaction_date=" + transaction_date + ", priority_flag=" + priority_flag + ", processing_fee="
				+ processing_fee + "]";
	}
    
	
}
