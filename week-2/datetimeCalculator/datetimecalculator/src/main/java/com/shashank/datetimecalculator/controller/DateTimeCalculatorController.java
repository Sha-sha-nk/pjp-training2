package com.shashank.datetimecalculator.controller;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shashank.datetimecalculator.model.FormEvent;
import com.shashank.datetimecalculator.repository.DateTimeCalculatorSessionDTO;


@Controller
public class DateTimeCalculatorController {
	
	@Autowired
	private DateTimeCalculatorSessionDTO dtcdto;
	
	@PersistenceContext
	public EntityManager em;
	
	@GetMapping(path="/calculate")
	public String showForm(Model model){
		FormEvent formEvent = new FormEvent();
	    model.addAttribute("FormEvent",formEvent);
	    List<String> menuOptions = Arrays.asList("Add", "Subtract");
        model.addAttribute("menuOptions", menuOptions);
		return "calculate_form";
	}
	
	@PostMapping(path="/calculate")
	public String submitForm(@ModelAttribute("FormEvent") FormEvent formEvent) {
		System.out.println(formEvent);
		Date answer = formEvent.calculate();
		formEvent.setAnswer(answer);
		dtcdto.save(formEvent);
		return "answer";
	}
	
	@GetMapping(path="/recent100logs")
	public String recentLogs(Model model) {
		String queryToGetLogs ="SELECT log from FormEvent log \r\n"+
	                           "order by log.session_id Desc";
		TypedQuery<FormEvent> query =em.createQuery(queryToGetLogs,FormEvent.class);
		List<FormEvent> sessions = query.setMaxResults(100).getResultList();
		model.addAttribute("session_table",sessions);
		return "logTable";
	}
}
