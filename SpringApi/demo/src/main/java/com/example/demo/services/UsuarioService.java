package com.example.demo.services;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.mappers.UsuarioMapper;
import com.example.demo.models.AreaModel;
import com.example.demo.models.RolModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UsuarioMapper mapper;

    public List<UsuarioDTO> obtenerUsuarios() {
        List<UsuarioModel> usuarios = (List<UsuarioModel>) usuarioCrudRepository.findAll();
        List<UsuarioModel> listaModelos = new ArrayList<>();
        usuarios.forEach(usuarioModel -> {
            if (!usuarioModel.isBorrado()) {
                listaModelos.add(usuarioModel);
            }
        });
        return mapper.toUsuariosDTO(listaModelos);
    }

    public Optional<UsuarioDTO> obtenerUsuario(int usuarioId) {
        return usuarioCrudRepository.findById(usuarioId).map(usuarioModel ->
                mapper.toUsuarioDTO(usuarioModel));
    }
    
    public UsuarioDTO obtenerUsuarioUsername(String username) {
    	return mapper.toUsuarioDTO(usuarioCrudRepository.findByUsuario(username));
    }

    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioModel usuario = mapper.toUsuario(usuarioDTO);
        return mapper.toUsuarioDTO(usuarioCrudRepository.save(usuario));
    }

    public UsuarioDTO actualizarUsuario(int usuarioId, UsuarioDTO usuarioDTO) {
        try {
            Optional<UsuarioDTO> modelo =
                    usuarioCrudRepository.findById(usuarioId).map(usuario ->
                            mapper.toUsuarioDTO(usuario));

            if (modelo.isPresent()) {
                UsuarioDTO u = new UsuarioDTO();
                u.setUserId(modelo.get().getUserId());
                u.setTypeIdentity((usuarioDTO.getTypeIdentity() == null) ? modelo.get().getTypeIdentity() : usuarioDTO.getTypeIdentity() );
                u.setNumberIdentity((usuarioDTO.getNumberIdentity() == 0) ? modelo.get().getNumberIdentity() : usuarioDTO.getNumberIdentity());
                u.setFirstName((usuarioDTO.getFirstName() == null) ? modelo.get().getFirstName() : usuarioDTO.getFirstName());
                u.setSecondName((usuarioDTO.getSecondName() == null) ? modelo.get().getSecondName() : usuarioDTO.getSecondName());
                u.setFirstLastName((usuarioDTO.getFirstLastName() == null) ? modelo.get().getFirstLastName() : usuarioDTO.getFirstLastName());
                u.setSecondLastName((usuarioDTO.getSecondLastName() == null) ? modelo.get().getSecondLastName() : usuarioDTO.getSecondLastName());
                u.setEmail((usuarioDTO.getEmail() == null) ? modelo.get().getEmail() : usuarioDTO.getEmail());
                u.setUser((usuarioDTO.getUser() == null) ? modelo.get().getUser() : usuarioDTO.getUser());
                u.setPass((usuarioDTO.getPass() == null) ? modelo.get().getPass() : usuarioDTO.getPass());
                u.setPhone((usuarioDTO.getPhone() == 0) ? modelo.get().getPhone() : usuarioDTO.getPhone());
                u.setRole((usuarioDTO.getRole() == null) ? modelo.get().getRole() : usuarioDTO.getRole());
                u.setArea((usuarioDTO.getArea() == null) ? modelo.get().getArea() : usuarioDTO.getArea());

                return mapper.toUsuarioDTO(usuarioCrudRepository.save(mapper.toUsuario(u)));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    public boolean eliminarUsuario(int usuarioId) {
        try {
            UsuarioModel a = usuarioCrudRepository.findById(usuarioId).get();
            a.setBorrado(true);
            usuarioCrudRepository.save(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
