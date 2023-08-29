package com.example.demo.services;

import com.example.demo.dto.AreaDTO;
import com.example.demo.mappers.AreaMapper;
import com.example.demo.models.AreaModel;
import com.example.demo.repositories.AreaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaCrudRepository areaCrudRepository;

    @Autowired
    AreaMapper mapper;

    public List<AreaDTO> obtenerAreas() {
        List<AreaModel> modelos = (List<AreaModel>) areaCrudRepository.findAll();
        List<AreaModel> listaAreas = new ArrayList<>();
        modelos.forEach(areaModel -> {
            if (!areaModel.isBorrado()) {
                listaAreas.add(areaModel);
            }
        });
        return mapper.toAreas(listaAreas);
    }

    public Optional<AreaDTO> obtenerArea(int modelId) {
        return areaCrudRepository.findById(modelId).map(modelo ->
                mapper.toAreDTO(modelo));
    }

    public AreaDTO guardarArea(AreaDTO areaDTO) {
        AreaModel area = mapper.toArea(areaDTO);
        return mapper.toAreDTO(areaCrudRepository.save(area));
    }

    public AreaDTO actualizarArea(int areaId, AreaDTO areaDTO) {
        try {
            Optional<AreaDTO> modelo =
                    areaCrudRepository.findById(areaId).map(area ->
                            mapper.toAreDTO(area));

            if (modelo.isPresent()) {
                AreaDTO a = new AreaDTO();
                a.setAreaId(modelo.get().getAreaId());
                a.setName(areaDTO.getName());

                return mapper.toAreDTO(areaCrudRepository.save(mapper.toArea(a)));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean eliminarModelo(int modeloId) {
        try {
            AreaModel a = areaCrudRepository.findById(modeloId).get();
            a.setBorrado(true);
            areaCrudRepository.save(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
