package com.example.demo.controllers;

import com.example.demo.dto.ModeloDTO;
import com.example.demo.models.ModeloModel;
import com.example.demo.services.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modelo")
@CrossOrigin(origins = "*")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping("/all")
    public List<ModeloDTO> obtenerModelos() {
        return modeloService.obtenerModelos();
    }

    @GetMapping(path = "/{id}")
    public Optional<ModeloDTO> obtenerModelo(@PathVariable("id") int modeloId) {
        return modeloService.obtenerModelo(modeloId);
    }

    @PostMapping()
    public ModeloDTO guardarModelo(@RequestBody ModeloDTO modelo) {
        return modeloService.guardarModelo(modelo);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ModeloDTO> actualizarModelo(@PathVariable("id") int modeloId, @RequestBody ModeloDTO modeloDTO) {
        try {
            ModeloDTO respuesta = modeloService.actualizarModelo(modeloId, modeloDTO);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ModeloDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "delete/{Id}")
    public ResponseEntity<ModeloDTO> eliminarModelo(@PathVariable("Id") int modeloId) {
        boolean respuesta = modeloService.eliminarModelo(modeloId);
        if (respuesta) {
            return new ResponseEntity<>(new ModeloDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ModeloDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
