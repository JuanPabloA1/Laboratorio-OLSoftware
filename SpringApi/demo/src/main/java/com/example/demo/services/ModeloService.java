package com.example.demo.services;

import com.example.demo.dto.ModeloDTO;
import com.example.demo.mappers.ModeloMapping;
import com.example.demo.models.AreaModel;
import com.example.demo.models.FabricanteModel;
import com.example.demo.models.ModeloModel;
import com.example.demo.repositories.ModeloCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    @Autowired
    private ModeloCrudRepository modeloCrudRepository;

    @Autowired
    private ModeloMapping mapper;

    public List<ModeloDTO> obtenerModelos() {
        List<ModeloModel> modelos = (List<ModeloModel>) modeloCrudRepository.findAll();
        List<ModeloModel> listaModelos = new ArrayList<>();
        modelos.forEach(modeloModel -> {
            if (!modeloModel.isBorrado()) {
                listaModelos.add(modeloModel);
            }
        });
        return mapper.toModelos(listaModelos);
    }

    public Optional<ModeloDTO> obtenerModelo(int modeloId) {
        return modeloCrudRepository.findById(modeloId).map(modelo ->
                mapper.toModeloDTO(modelo));
    }

    public ModeloDTO guardarModelo(ModeloDTO modeloDTO) {
        ModeloModel modelo = mapper.toModelo(modeloDTO);
        return mapper.toModeloDTO(modeloCrudRepository.save(modelo));
    }

    public ModeloDTO actualizarModelo(int modeloId, ModeloDTO modeloDTO) {
        try {
            Optional<ModeloDTO> model = modeloCrudRepository.findById(modeloId).map(modelo ->
                    mapper.toModeloDTO(modelo));

            if (model.isPresent()) {
                ModeloDTO m = new ModeloDTO();
                m.setModelId(model.get().getModelId());
                m.setName(modeloDTO.getName() == null ? model.get().getName() : modeloDTO.getName());
                m.setFactory(modeloDTO.getFactory() == null ? model.get().getFactory() : modeloDTO.getFactory());

                return mapper.toModeloDTO(modeloCrudRepository.save(mapper.toModelo(m)));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean eliminarModelo(int modeloId) {
        try {
            ModeloModel a = modeloCrudRepository.findById(modeloId).get();
            a.setBorrado(true);
            modeloCrudRepository.save(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
