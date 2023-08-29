package com.example.demo.controllers;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping( path = "/{id}")
    public Optional<UsuarioDTO> obtenerUsuario(@PathVariable("id") int usuarioId) {
        return usuarioService.obtenerUsuario(usuarioId);
    }
    
    @GetMapping("obtener/{usuario}")
    public UsuarioDTO obtenerUsuarioNombre(@PathVariable("usuario") String nombreUsuario) {
    	return usuarioService.obtenerUsuarioUsername(nombreUsuario);
    }

    @PostMapping()
    public UsuarioDTO guardarUsuario(@RequestBody UsuarioDTO usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity actualizarUsuario(@PathVariable("id") int usuarioId, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO respuesta = usuarioService.actualizarUsuario(usuarioId, usuarioDTO);
        if (respuesta != null) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<UsuarioDTO> eliminarUsuario(@PathVariable("id") int usuarioId) {
        boolean respuesta = usuarioService.eliminarUsuario(usuarioId);
        if (respuesta) {
            return new ResponseEntity<>(new UsuarioDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new UsuarioDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
