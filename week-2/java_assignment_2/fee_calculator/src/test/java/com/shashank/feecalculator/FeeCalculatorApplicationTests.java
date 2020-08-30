package com.shashank.feecalculator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class FeeCalculatorApplicationTests {
    
	@PersistenceContext
	public EntityManager em;
	
	@Test
	void test1() {
		assertTrue(true);
	}

}
