package com.shashank.feecalculator.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.shashank.feecalculator.model.Report;
import com.shashank.feecalculator.repository.ClientTransactionReportDTO;
import com.shashank.feecalculator.service.ClientTransactionService;

@RestController
public class FeeCalculatorController {
   
	@Autowired
	private ClientTransactionService cts;
	
	@Autowired
	private ClientTransactionReportDTO ctrdto;
	
	@PersistenceContext
	public EntityManager em;
	
	@RequestMapping(path="/loadClientTransactionData/csv")
	public void setDataInDB() throws IOException {
		cts.saveClientTransactionData();
	}
	
	@GetMapping(path="/generateReport/csv")
	@Transactional
	public String generateTransactionReport() {
		
		//creating blank report
		createBlank();
		
		//Intraday processing
	    //intradayProcessing();
	    
		//charge $500
		charge500();
		
		//charge $100
		charge100();
	    
		//charge $50
		charge50();
		
		
		//writing to csv file
		List<Report> r = getClientReport();
		String csvOutputFileName = "src/main/resources/Sample_Output.csv";
        writeCSVFile(csvOutputFileName, r);
		return "Sample_Output.csv file generated successfully";
	}
    

	private List<Report> getClientReport() {
		String queryGetFinalReport ="SELECT r FROM Report r \r\n "+ 
	                                "ORDER BY r.client_id,r.transaction_type,r.transaction_date,r.priority_flag";
        TypedQuery<Report> query=em.createQuery(queryGetFinalReport,Report.class);
        List<Report> r = query.getResultList();
        for(Report i : r)
        {  
           em.refresh(i);
           System.out.println(i);
        }
		return r;
	}

	private void createBlank() {
		String queryCreateBlank ="SELECT distinct NEW com.shashank.feecalculator.model.Report(c.client_id,c.transaction_type,c.transaction_date,c.priority_flag,0 as processing_fee)\r\n" + 
		                         "from ClientTransaction as c\r\n" + 
		                         "order by c.client_id,c.transaction_type,c.transaction_date,c.priority_flag";
        TypedQuery<Report> query1 =em.createQuery(queryCreateBlank,Report.class);
        List<Report> r = query1.getResultList();
        for(Report i : r)
        {    
	        ctrdto.save(i);
	        System.out.println(i);
        }
	}

	@Transactional
	private void charge500() {
		String queryUpdateHigh500 = "update Report cr\r\n" + 
				"set processing_fee=processing_fee+500\r\n" + 
				"where cr.priority_flag='Y'";
		Query query =em.createQuery(queryUpdateHigh500);
		int updateCount = query.executeUpdate();
		if(updateCount>0)
		{
			System.out.println(updateCount+" $500 transaction Updates done!");
		}
		else 
		{
			System.out.println("No Update");
		}
	}

	@Transactional
	private void charge100() {
		String queryUpdateNormal100 = "update Report cr\r\n" + 
				"set processing_fee=processing_fee+100\r\n" + 
				"where cr.priority_flag='N' and cr.transaction_type in ('SELL','WITHDRAW') ";
		Query query =em.createQuery(queryUpdateNormal100);
		int updateCount = query.executeUpdate();
		if(updateCount>0)
		{
			System.out.println(updateCount+" $100 transaction Updates done for nominal priority and type:(SELL,WITHDRAW)!");
		}
		else 
		{
			System.out.println("No Update");
		}
	}
	
	@Transactional
	private void charge50() {
		String queryUpdateNormal50 = "update Report cr\r\n" + 
				"set processing_fee=processing_fee+50\r\n" + 
				"where cr.priority_flag='N' and cr.transaction_type in ('BUY','DEPOSIT') ";
		Query query =em.createQuery(queryUpdateNormal50);
		int updateCount = query.executeUpdate();
		if(updateCount>0)
		{
			System.out.println(updateCount+" $50 transaction Updates done for nominal priority and type:(BUY,DEPOSIT)!");
		}
		else 
		{
			System.out.println("No Update");
		}
		
	}

	@Transactional
	private void intradayProcessing() {
		String queryUpdateIntraday = "UPDATE Report cr\r\n" + 
				"SET processing_fee=10\r\n" + 
				"FROM (SELECT b.client_id,b.transaction_type,b.transaction_date,b.priority_flag FROM ClientTransaction as b\r\n" + 
				"     Join ClientTransaction as s \r\n" + 
				"     on b.client_id=s.client_id AND b.security_id=s.security_id AND b.transaction_date=s.transaction_date \r\n" + 
				"     WHERE (b.transaction_type='BUY'and s.transaction_type='SELL') or (b.transaction_type='SELL'and s.transaction_type='BUY')\r\n" + 
				"	 ) intraday\r\n" + 
				"WHERE intraday.client_id=cr.client_id \r\n" + 
				"   and intraday.transaction_type=cr.transaction_type \r\n" + 
				"   and intraday.transaction_date=cr.transaction_date\r\n" + 
				"   and intraday.priority_flag=cr.priority_flag";
		Query query2 =em.createQuery(queryUpdateIntraday);
		int updateCount = query2.executeUpdate();
		if(updateCount>0)
		{
			System.out.println(updateCount+" Updates done!");
		}
		else 
		{
			System.out.println("No Update");
		}
		
	}

	private void writeCSVFile(String csvOutputFileName, List<Report> r) {
		ICsvBeanWriter beanWriter = null;
	    CellProcessor[] processors = new CellProcessor[] {
	            new NotNull(), // client_id
	            new NotNull(), // transaction_type
	            new NotNull(), // transaction_date
	            new NotNull(), // priority_flag
	            new ParseInt() // processing_fee
	    };
	 
	    try {
	        beanWriter = new CsvBeanWriter(new FileWriter(csvOutputFileName),
	                CsvPreference.STANDARD_PREFERENCE);
	        String[] header = {"client_id", "transaction_type","transaction_date","priority_flag","processing_fee"};
	        beanWriter.writeHeader(header);
	 
	        for (Report i : r) {
	            beanWriter.write(i, header, processors);
	        }
	 
	    } catch (IOException ex) {
	        System.err.println("Error writing the CSV file: " + ex);
	    } finally {
	        if (beanWriter != null) {
	            try {
	                beanWriter.close();
	            } catch (IOException ex) {
	                System.err.println("Error closing the writer: " + ex);
	            }
	        }
	    }
		
	}
	
}
