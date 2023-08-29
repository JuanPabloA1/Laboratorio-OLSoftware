package com.example.demo.mappers;

import com.example.demo.dto.RolDTO;
import com.example.demo.models.RolModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {
    @Mappings(value = {
            @Mapping(source = "idRol", target = "rolId"),
            @Mapping(source = "nombre", target = "name")
    })
    RolDTO toRolDTO(RolModel rol);
    List<RolDTO> toRolsDTO(List<RolModel> roles);

    @InheritInverseConfiguration
    @Mapping(target = "usuarios", ignore = true)
    RolModel toRol(RolDTO rol);
}
