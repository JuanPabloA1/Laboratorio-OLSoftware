package com.example.demo.repositories;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrudRepository extends CrudRepository<UsuarioModel, Integer> {
    public UsuarioModel findByUsuario(String user);
}
