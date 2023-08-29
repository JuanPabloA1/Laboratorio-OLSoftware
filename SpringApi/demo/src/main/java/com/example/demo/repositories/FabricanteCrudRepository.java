package com.example.demo.repositories;

import com.example.demo.models.FabricanteModel;
import org.springframework.data.repository.CrudRepository;

public interface FabricanteCrudRepository extends CrudRepository<FabricanteModel, Integer> {
}
