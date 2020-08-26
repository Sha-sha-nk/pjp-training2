package com.shashank.incomeevaluator.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shashank.incomeevaluator.model.LocationIncome;
import com.shashank.incomeevaluator.repository.LocationIncomeDTO;


@Service
public class LocationIncomeService {
        
	@Autowired
	private LocationIncomeDTO ldto;
	
	
	String line="";
	public void saveLocationIncomeData() throws IOException {
		BufferedReader br = null;
		try 
		{
		   br=new BufferedReader(new FileReader("src/main/resources/Sample_Input.csv"));
		   br.readLine();
		   while((line=br.readLine())!=null) {
			   String [] data=line.split(",");
			   LocationIncome li=new LocationIncome();
			   li.setCity(data[0]);
			   li.setCountry(data[1]);
			   li.setGender(data[2]);
			   li.setCurrency(data[3]);
			   li.setAverage_income(Double.parseDouble(data[4]));
			   ldto.save(li);
		   }
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			br.close();
		}
	}
	
	
}
