package com.shashank.datetimecalculator.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="session_log")
public class FormEvent {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int session_id;
	private Date date1;
	private Date date2;
	private Date answer;
	private String menu;
	public int getSession_id() {
		return session_id;
	}
	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public Date getAnswer() {
		return answer;
	}
	public void setAnswer(Date answer) {
		this.answer = answer;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	@Override
	public String toString() {
		return "FormEvent [session_id=" + session_id + ", date1=" + date1 + ", date2=" + date2 + ", answer=" + answer
				+ ", menu=" + menu + "]";
	}
	public Date calculate() {
		System.out.println(menu);
		if(menu.equals("Add"))
		{   
			System.out.println(menu);
			return this.addDates();
		}
		else if(menu.equals("Subtract"))
		{
			System.out.println(menu);
			return this.subtractDates();
		}
		return null;
	}
	private Date subtractDates() {
		long difference_In_Time = date1.getTime() - date2.getTime();
		
		return new Date(difference_In_Time);
	}
	private Date addDates() {
        long sum_dates = date1.getTime()+date2.getTime();
		return new Date(sum_dates);
	}
	
	
	
}
