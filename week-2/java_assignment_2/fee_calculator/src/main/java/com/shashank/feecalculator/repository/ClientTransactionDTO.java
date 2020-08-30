package com.shashank.feecalculator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shashank.feecalculator.model.ClientTransaction;

@Repository
public interface ClientTransactionDTO extends CrudRepository<ClientTransaction,Integer>{

}
