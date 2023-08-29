package com.example.demo.controllers;

import com.example.demo.dto.FabricanteDTO;
import com.example.demo.services.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fabricante")
@CrossOrigin(origins = "*")
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;

    @GetMapping("/all")
    public List<FabricanteDTO> obtenerFabricantes() {
        return fabricanteService.obtenerFabricantes();
    }

    @GetMapping("/{id}")
    public Optional<FabricanteDTO> obtenerFabricante(int fabricanteId) {
        return fabricanteService.obtenerFabricante(fabricanteId);
    }

    @PostMapping()
    public FabricanteDTO guardarFabricante(@RequestBody FabricanteDTO fabricante) {
        return fabricanteService.guardarFabricante(fabricante);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<FabricanteDTO> actualizarFabricante(@PathVariable("id") int fabricanteId, @RequestBody FabricanteDTO fabricante) {
        try {
            FabricanteDTO respuesta = fabricanteService.actualizarFabricante(fabricanteId, fabricante);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new FabricanteDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<FabricanteDTO> eliminarFabricante(@PathVariable("id") int fabricanteId) {
        boolean respuesta = fabricanteService.eliminarFabricante(fabricanteId);
        if (respuesta) {
            return new ResponseEntity<>(new FabricanteDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new FabricanteDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
