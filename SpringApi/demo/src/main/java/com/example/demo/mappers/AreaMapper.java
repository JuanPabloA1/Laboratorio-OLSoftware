package com.example.demo.mappers;

import com.example.demo.dto.AreaDTO;
import com.example.demo.models.AreaModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    @Mappings(value = {
            @Mapping(source = "idArea", target = "areaId"),
            @Mapping(source = "nombre", target = "name"),
    })
    AreaDTO toAreDTO(AreaModel area);
    List<AreaDTO> toAreas(List<AreaModel> areas);

    @InheritInverseConfiguration
    AreaModel toArea(AreaDTO area);
}
