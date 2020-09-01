package com.shashank.datetimecalculator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shashank.datetimecalculator.model.FormEvent;

@Repository
public interface DateTimeCalculatorSessionDTO extends CrudRepository<FormEvent,Integer>{

}



