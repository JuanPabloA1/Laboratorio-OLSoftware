package com.example.demo.controllers;

import com.example.demo.dto.DispositivoDTO;
import com.example.demo.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/dispositivo")
@CrossOrigin(origins = "*")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("/all")
    public List<DispositivoDTO> obtenerDispositivos() {
        return dispositivoService.obtenerDispositivos();
    }

    @GetMapping("/{id}")
    public Optional<DispositivoDTO> obtenerDispositivo(@PathVariable("id") int dispositivoId) {
        return dispositivoService.obtenerDispositivo(dispositivoId);
    }

    @PostMapping()
    public DispositivoDTO guardarDispositivo(@RequestBody DispositivoDTO dispositivoDTO) {
        return dispositivoService.guardarDispositivo(dispositivoDTO);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<DispositivoDTO> actualizarDispositivo(@PathVariable("id") int dispositivoId, @RequestBody DispositivoDTO dispositivoDTO) {
        try {
            DispositivoDTO respuesta = dispositivoService.actualizarDispositivo(dispositivoId, dispositivoDTO);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new DispositivoDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DispositivoDTO> eliminarDispositivo(@PathVariable("id") int dispositivoId) {
        boolean respuesta = dispositivoService.eliminarDispositivo(dispositivoId);
        if (respuesta) {
            return new ResponseEntity<>(new DispositivoDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new DispositivoDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
