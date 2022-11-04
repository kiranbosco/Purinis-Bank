package com.coding.exercise.bankapp.repository;

import com.coding.exercise.bankapp.model.CustomerAccountXRef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountXRefRepository extends CrudRepository<CustomerAccountXRef, String> {

}
