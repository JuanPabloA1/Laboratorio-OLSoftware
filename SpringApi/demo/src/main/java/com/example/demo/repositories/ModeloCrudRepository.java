package com.example.demo.repositories;

import com.example.demo.models.ModeloModel;
import org.springframework.data.repository.CrudRepository;

public interface ModeloCrudRepository extends CrudRepository<ModeloModel, Integer> {
}
