package com.example.demo.mappers;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.models.UsuarioModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RolMapper.class, AreaMapper.class})
public interface UsuarioMapper {
    @Mappings(value = {
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "tipoIdentificacion", target = "typeIdentity"),
            @Mapping(source = "numeroIdentificacion", target = "numberIdentity"),
            @Mapping(source = "primerNombre", target = "firstName"),
            @Mapping(source = "segundoNombre", target = "secondName"),
            @Mapping(source = "primerApellido", target = "firstLastName"),
            @Mapping(source = "segundoApellido", target = "secondLastName"),
            @Mapping(source = "correoElectronico", target = "email"),
            @Mapping(source = "usuario", target = "user"),
            @Mapping(source = "contrasena", target = "pass"),
            @Mapping(source = "telefono", target = "phone"),
            @Mapping(source = "rol", target = "role"),
            @Mapping(source = "area", target = "area")
    })
    UsuarioDTO toUsuarioDTO(UsuarioModel usuario);
    List<UsuarioDTO> toUsuariosDTO(List<UsuarioModel> usuarios);

    @InheritInverseConfiguration
    UsuarioModel toUsuario(UsuarioDTO usuario);
}
