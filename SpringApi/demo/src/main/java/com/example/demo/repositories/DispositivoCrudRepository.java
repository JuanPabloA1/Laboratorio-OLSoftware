package com.example.demo.repositories;

import com.example.demo.models.DispositivoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoCrudRepository extends JpaRepository<DispositivoModel, Integer> {
}
