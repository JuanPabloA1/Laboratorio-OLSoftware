package com.example.demo.repositories;

import com.example.demo.models.AreaModel;
import org.springframework.data.repository.CrudRepository;

public interface AreaCrudRepository extends CrudRepository<AreaModel, Integer> {
}
