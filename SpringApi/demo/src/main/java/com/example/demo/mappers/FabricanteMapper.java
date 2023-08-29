package com.example.demo.mappers;

import com.example.demo.dto.FabricanteDTO;
import com.example.demo.models.FabricanteModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FabricanteMapper {
    @Mappings(value = {
            @Mapping(source = "idFabricante", target = "factoryId"),
            @Mapping(source = "nombre", target = "name")
    })
    FabricanteDTO toFabricanteDTO(FabricanteModel fabricante);
    List<FabricanteDTO> toFabricantes(List<FabricanteModel> fabricantes);

    @InheritInverseConfiguration
    FabricanteModel toFabricante(FabricanteDTO fabricante);
}
