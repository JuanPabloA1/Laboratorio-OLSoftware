package com.example.demo.controllers;

import com.example.demo.dto.RolDTO;
import com.example.demo.models.RolModel;
import com.example.demo.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "*")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/all")
    public List<RolDTO> obtenerRoles() {
        return rolService.obtenerRoles();
    }

    @GetMapping( path = "/{id}")
    public Optional<RolDTO> obtenerRole(@PathVariable("id") int roleId) {
        return rolService.obtenerRole(roleId);
    }

    @PostMapping()
    public RolDTO guardarRole(@RequestBody RolDTO rolDTO) {
        return rolService.guardarRole(rolDTO);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<RolDTO> actualizarRole(@PathVariable("id") int roleId, @RequestBody RolDTO rolDTO) {
        try {
            RolDTO respuesta = rolService.actualizarRole(roleId, rolDTO);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RolDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RolDTO> eliminarRole(@PathVariable("id") int roleId) {
        boolean respuesta = rolService.eliminarRole(roleId);
        if (respuesta) {
            return new ResponseEntity<>(new RolDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new RolDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
