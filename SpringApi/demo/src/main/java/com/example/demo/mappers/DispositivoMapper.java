package com.example.demo.mappers;

import com.example.demo.dto.DispositivoDTO;
import com.example.demo.models.DispositivoModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AreaMapper.class, FabricanteMapper.class, ModeloMapping.class})
public interface DispositivoMapper {
    @Mappings(value = {
            @Mapping(source = "idDispositivo", target = "deviceId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "area", target = "area"),
            @Mapping(source = "estadoDispositivo", target = "stateDevice"),
            @Mapping(source = "tipoDispositivo", target = "typeDevice"),
            @Mapping(source = "fabricante", target = "factory"),
            @Mapping(source = "modelo", target = "model"),
            @Mapping(source = "numeroSerie", target = "numberSerie"),
            /*@Mapping(source = "numeroInventario", target = "numberInventory"),*/
            @Mapping(source = "comentario", target = "coment")
    })
    DispositivoDTO toDispositivoDTO(DispositivoModel dispositivo);
    List<DispositivoDTO> toDispositivosDTO(List<DispositivoModel> dispositivos);

    @InheritInverseConfiguration
    DispositivoModel toDispositivo(DispositivoDTO dispositivo);
}
