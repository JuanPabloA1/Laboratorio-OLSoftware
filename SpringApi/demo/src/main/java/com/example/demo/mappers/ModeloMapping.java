package com.example.demo.mappers;

import com.example.demo.dto.ModeloDTO;
import com.example.demo.models.ModeloModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FabricanteMapper.class})
public interface ModeloMapping {
    @Mappings(value = {
            @Mapping(source = "idModelo", target = "modelId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "fabricante", target = "factory")
    })
    ModeloDTO toModeloDTO(ModeloModel modelo);
    List<ModeloDTO> toModelos(List<ModeloModel> modelos);

    @InheritInverseConfiguration
    ModeloModel toModelo(ModeloDTO modelo);
}
