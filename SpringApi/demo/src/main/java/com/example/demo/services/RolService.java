package com.example.demo.services;

import com.example.demo.dto.RolDTO;
import com.example.demo.mappers.RolMapper;
import com.example.demo.models.AreaModel;
import com.example.demo.models.ModeloModel;
import com.example.demo.models.RolModel;
import com.example.demo.repositories.RolCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolCrudRepository rolCrudRepository;

    @Autowired
    private RolMapper mapper;

    public List<RolDTO> obtenerRoles() {
        List<RolModel> roles = (List<RolModel>) rolCrudRepository.findAll();
        List<RolModel> listaRoles = new ArrayList<>();
        roles.forEach(rolModel -> {
            if (!rolModel.isBorrado()) {
                listaRoles.add(rolModel);
            }
        });
        return mapper.toRolsDTO(listaRoles);
    }

    public Optional<RolDTO> obtenerRole(int roleId) {
        return rolCrudRepository.findById(roleId).map(rol ->
            mapper.toRolDTO(rol));
    }

    public RolDTO guardarRole(RolDTO rolDTO) {
        RolModel role = mapper.toRol(rolDTO);
        return mapper.toRolDTO(rolCrudRepository.save(role));
    }

    public RolDTO actualizarRole(int roleId, RolDTO role) {
        try {
            Optional<RolDTO> model =
                    rolCrudRepository.findById(roleId).map(rol ->
                            mapper.toRolDTO(rol));

            if (model.isPresent()) {
                RolDTO r = new RolDTO();
                r.setRolId(model.get().getRolId());
                r.setName(role.getName() == null ? model.get().getName() : role.getName());

                return mapper.toRolDTO(rolCrudRepository.save(mapper.toRol(r)));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean eliminarRole(int roleId) {
        try {
            RolModel a = rolCrudRepository.findById(roleId).get();
            a.setBorrado(true);
            rolCrudRepository.save(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
