package com.example.demo.services;

import com.example.demo.dto.FabricanteDTO;
import com.example.demo.mappers.FabricanteMapper;
import com.example.demo.models.AreaModel;
import com.example.demo.models.DispositivoModel;
import com.example.demo.models.FabricanteModel;
import com.example.demo.repositories.FabricanteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteCrudRepository fabricanteCrudRepository;

    @Autowired
    private FabricanteMapper mapper;

    public List<FabricanteDTO> obtenerFabricantes() {
        List<FabricanteModel> fabricantes = (List<FabricanteModel>) fabricanteCrudRepository.findAll();
        List<FabricanteModel> listaFabricante = new ArrayList<>();
        fabricantes.forEach(fabricanteModel -> {
            if (!fabricanteModel.isBorrado()) {
                listaFabricante.add(fabricanteModel);
            }
        });
        return mapper.toFabricantes(listaFabricante);
    }

    public Optional<FabricanteDTO> obtenerFabricante(int fabricanteId) {
        return fabricanteCrudRepository.findById(fabricanteId).map(fabricante ->
                mapper.toFabricanteDTO(fabricante));
    }

    public FabricanteDTO guardarFabricante(FabricanteDTO fabricanteDTO) {
        FabricanteModel fabricante = mapper.toFabricante(fabricanteDTO);
        return mapper.toFabricanteDTO(fabricanteCrudRepository.save(fabricante));
    }

    public FabricanteDTO actualizarFabricante(int fabricanteId, FabricanteDTO fabri) {
        try {
            Optional<FabricanteDTO> fabricanteDTO = fabricanteCrudRepository.findById(fabricanteId).map(fabricante ->
                mapper.toFabricanteDTO(fabricante));

            if (fabricanteDTO.isPresent()) {
                FabricanteDTO f = new FabricanteDTO();
                f.setFactoryId(fabricanteDTO.get().getFactoryId());
                f.setName(fabri.getName() == null ? fabricanteDTO.get().getName() : fabri.getName());

                return mapper.toFabricanteDTO(fabricanteCrudRepository.save(mapper.toFabricante(f)));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean eliminarFabricante(int fabricanteId) {
        try {
            FabricanteModel a = fabricanteCrudRepository.findById(fabricanteId).get();
            a.setBorrado(true);
            fabricanteCrudRepository.save(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
