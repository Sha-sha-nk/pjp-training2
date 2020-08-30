package com.shashank.feecalculator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shashank.feecalculator.model.Report;

@Repository
public interface ClientTransactionReportDTO extends CrudRepository<Report,Integer>{

}
