package com.shashank.feecalculator.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shashank.feecalculator.model.ClientTransaction;
import com.shashank.feecalculator.repository.ClientTransactionDTO;


@Service
public class ClientTransactionService {
        
	@Autowired
	private ClientTransactionDTO ctdto;
	private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	String line="";
	public void saveClientTransactionData() throws IOException {
		BufferedReader br = null;
		try 
		{
		   br=new BufferedReader(new FileReader("src/main/resources/Sample_Input.csv"));
		   br.readLine();
		   while((line=br.readLine())!=null) {
			   String [] data=line.split(",");
			   ClientTransaction ct=new ClientTransaction();
			   ct.setExternal_transaction_id(data[0]);
			   ct.setClient_id(data[1]);
			   ct.setSecurity_id(data[2]);
			   ct.setTransaction_type(data[3]);
			   String date = data[4];
			   try {
				ct.setTransaction_date(formatter.parse(date));
			   } 
			   catch (ParseException e) {
				e.printStackTrace();
			   }
			   ct.setMarket_value(Double.parseDouble(data[5]));
			   ct.setPriority_flag(data[6]);
			   ctdto.save(ct);
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
