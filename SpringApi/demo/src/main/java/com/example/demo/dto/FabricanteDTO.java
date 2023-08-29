package com.example.demo.dto;

import com.example.demo.models.DispositivoModel;
import com.example.demo.models.ModeloModel;

import javax.persistence.OneToMany;
import java.util.List;

public class FabricanteDTO {
    private Integer factoryId;
    private String name;

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
