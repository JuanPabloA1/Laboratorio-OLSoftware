package com.example.demo.services;

import com.example.demo.dto.DispositivoDTO;
import com.example.demo.mappers.DispositivoMapper;
import com.example.demo.models.AreaModel;
import com.example.demo.models.DispositivoModel;
import com.example.demo.repositories.DispositivoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoCrudRepository dispositivoCrudRepository;

    @Autowired
    private DispositivoMapper mapper;

    public List<DispositivoDTO> obtenerDispositivos() {
        List<DispositivoModel> dispositivos = (List<DispositivoModel>) dispositivoCrudRepository.findAll();
        List<DispositivoModel> listaDispositivos = new ArrayList<>();
        dispositivos.forEach(dispositivoModel -> {
            if (!dispositivoModel.isBorrado()) {
                listaDispositivos.add(dispositivoModel);
            }
        });
        return mapper.toDispositivosDTO(listaDispositivos);
    }

    public Optional<DispositivoDTO> obtenerDispositivo(int dispositivoId) {
        return dispositivoCrudRepository.findById(dispositivoId).map(dispositivo ->
                mapper.toDispositivoDTO(dispositivo));
    }

    public DispositivoDTO guardarDispositivo(DispositivoDTO dispositivoDTO) {
        DispositivoModel dispositivo = mapper.toDispositivo(dispositivoDTO);
        return mapper.toDispositivoDTO(dispositivoCrudRepository.save(dispositivo));
    }

    public DispositivoDTO actualizarDispositivo(int dispositivoId, DispositivoDTO dispositivoDTO) {
        try {
            Optional<DispositivoDTO> disp = dispositivoCrudRepository.findById(dispositivoId).map(d ->
                    mapper.toDispositivoDTO(d));

            if (disp.isPresent()) {
                DispositivoDTO d = new DispositivoDTO();
                d.setDeviceId(disp.get().getDeviceId());
                d.setName((dispositivoDTO.getName() == null ? d.getName() : dispositivoDTO.getName()));
                d.setArea((dispositivoDTO.getArea() == null ? d.getArea() : dispositivoDTO.getArea()));
                d.setStateDevice((dispositivoDTO.getStateDevice() == null ? d.getStateDevice() : dispositivoDTO.getStateDevice()));
                d.setTypeDevice((dispositivoDTO.getTypeDevice() == null ? d.getTypeDevice() : dispositivoDTO.getTypeDevice()));
                d.setFactory((dispositivoDTO.getFactory() == null ? d.getFactory() : dispositivoDTO.getFactory()));
                d.setModel((dispositivoDTO.getModel() == null ? d.getModel() : dispositivoDTO.getModel()));
                d.setNumberSerie((dispositivoDTO.getNumberSerie() == null ? d.getNumberSerie() : dispositivoDTO.getNumberSerie()));
                /*d.setNumberInventory((dispositivoDTO.getNumberInventory() == null ? d.getNumberInventory() : dispositivoDTO.getNumberInventory()));*/

                return mapper.toDispositivoDTO(dispositivoCrudRepository.save(mapper.toDispositivo(d)));

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean eliminarDispositivo(int dispositivoId) {
        try {
            DispositivoModel a = dispositivoCrudRepository.findById(dispositivoId).get();
            a.setBorrado(true);
            dispositivoCrudRepository.save(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
