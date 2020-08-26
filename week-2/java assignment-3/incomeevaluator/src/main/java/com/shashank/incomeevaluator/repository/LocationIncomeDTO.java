package com.shashank.incomeevaluator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shashank.incomeevaluator.model.LocationIncome;

@Repository
public interface LocationIncomeDTO extends CrudRepository<LocationIncome,Integer>{

}
