package com.shashank.incomeevaluator.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.shashank.incomeevaluator.model.Result;
import com.shashank.incomeevaluator.service.LocationIncomeService;

@RestController
public class IncomeEvaluationController {
   
	@Autowired
	private LocationIncomeService lis;
	
	@PersistenceContext
	public EntityManager em;
	
	@RequestMapping(path="/loadLocationIncomeDataCSV")
	public void setDataInDB() throws IOException {
		lis.saveLocationIncomeData();
	}
	
	@GetMapping(path="/generateReportCSV")
	public String generateAverageIncomeReport() {
		String queryCountryNull ="SELECT NEW com.shashank.incomeevaluator.model.Result(i.city,i.gender,AVG(i.average_income*cv.conversion_factor)) FROM LocationIncome i\r\n" + 
				"JOIN CurrencyUSDConversion cv on i.currency=cv.currency\r\n" + 
				"WHERE i.country is NULL\r\n" + 
				"GROUP BY i.city,i.gender";
		String queryCountryNotNull ="SELECT NEW com.shashank.incomeevaluator.model.Result(i.country,i.gender,AVG(i.average_income*cv.conversion_factor)) FROM LocationIncome i\r\n" + 
				"JOIN CurrencyUSDConversion cv on i.currency=cv.currency\r\n" + 
				"WHERE i.country is NOT NULL\r\n" + 
				"GROUP BY i.country,i.gender";
		TypedQuery<Result> query1 =em.createQuery(queryCountryNull,Result.class);
		TypedQuery<Result> query2 =em.createQuery(queryCountryNotNull,Result.class);
		List<Result> r = query1.getResultList();
		r.addAll(query2.getResultList());
		for(Result i : r)
		{
			 System.out.println(i);
		}
		String csvOutputFileName = "src/main/resources/Sample_Output.csv";
        writeCSVFile(csvOutputFileName, r);
		return "Sample_Output.csv file generated successfully";
	}

	private void writeCSVFile(String csvOutputFileName, List<Result> r) {
		ICsvBeanWriter beanWriter = null;
	    CellProcessor[] processors = new CellProcessor[] {
	            new NotNull(), // city/country
	            new NotNull(), // gender
	            new ParseDouble() // averageincome in usd
	    };
	 
	    try {
	        beanWriter = new CsvBeanWriter(new FileWriter(csvOutputFileName),
	                CsvPreference.STANDARD_PREFERENCE);
	        String[] header = {"city_country", "gender",  "usdAverageIncome"};
	        beanWriter.writeHeader(header);
	 
	        for (Result i : r) {
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
