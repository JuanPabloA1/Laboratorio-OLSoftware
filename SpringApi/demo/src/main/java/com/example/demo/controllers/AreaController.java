package com.example.demo.controllers;

import com.example.demo.dto.AreaDTO;
import com.example.demo.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/area")
@CrossOrigin(origins = "*")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/all")
    public List<AreaDTO> obtenerAreas() {
        return areaService.obtenerAreas();
    }

    @GetMapping("/{id}")
    public Optional<AreaDTO> obtenerArea(@PathVariable("id") int areaid) {
        return areaService.obtenerArea(areaid);
    }

    @PostMapping()
    public AreaDTO guardarArea(@RequestBody AreaDTO areaDTO) {
        return areaService.guardarArea(areaDTO);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AreaDTO> actualizarArea(@PathVariable("id") int id, @RequestBody AreaDTO areaDTO) {
        try {
            AreaDTO respuesta = areaService.actualizarArea(id, areaDTO);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new AreaDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AreaDTO> eliminarArea(@PathVariable("id") int areaId) {
        boolean respuesta = areaService.eliminarModelo(areaId);
        if (respuesta) {
            return new ResponseEntity<>(new AreaDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AreaDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
