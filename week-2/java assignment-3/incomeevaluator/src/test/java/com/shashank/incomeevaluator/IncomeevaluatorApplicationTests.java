package com.shashank.incomeevaluator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.shashank.incomeevaluator.model.CurrencyUSDConversion;

@SpringBootTest
class IncomeevaluatorApplicationTests {
    
	@PersistenceContext
	public EntityManager em;
	
	@Test
	void ableToFetchConversionLookUpTable() {
		String queryStr ="SELECT c FROM CurrencyUSDConversion c";
        TypedQuery<CurrencyUSDConversion> query = em.createQuery(queryStr,CurrencyUSDConversion.class);
     	List<CurrencyUSDConversion> c = query.getResultList();
		for(CurrencyUSDConversion i : c)
		{
		   System.out.println(i);
		}
		System.out.println(c);
		assertTrue(c.size()==5);
	}

}
