package com.example.demo.dto;

public class ModeloDTO {
    private Integer modelId;
    private String name;
    private FabricanteDTO factory;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FabricanteDTO getFactory() {
        return factory;
    }

    public void setFactory(FabricanteDTO factory) {
        this.factory = factory;
    }

}
