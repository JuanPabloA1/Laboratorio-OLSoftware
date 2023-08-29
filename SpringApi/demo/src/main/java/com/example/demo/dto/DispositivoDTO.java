package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DispositivoDTO {
    private Integer deviceId;
    private String name;
    private AreaDTO area;
    private String stateDevice;
        private String typeDevice;
    private FabricanteDTO factory;
    private ModeloDTO model;
    private String numberSerie;
    /*private String numberInventory;*/
    private String coment;

    private boolean borrado;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaDTO getArea() {
        return area;
    }

    public void setArea(AreaDTO area) {
        this.area = area;
    }

    public String getStateDevice() {
        return stateDevice;
    }

    public void setStateDevice(String stateDevice) {
        this.stateDevice = stateDevice;
    }

    public String getTypeDevice() {
        return typeDevice;
    }

    public void setTypeDevice(String typeDevice) {
        this.typeDevice = typeDevice;
    }

    public FabricanteDTO getFactory() {
        return factory;
    }

    public void setFactory(FabricanteDTO factory) {
        this.factory = factory;
    }

    public ModeloDTO getModel() {
        return model;
    }

    public void setModel(ModeloDTO model) {
        this.model = model;
    }

    public String getNumberSerie() {
        return numberSerie;
    }

    public void setNumberSerie(String numberSerie) {
        this.numberSerie = numberSerie;
    }

    /*public String getNumberInventory() {
        return numberInventory;
    }

    public void setNumberInventory(String numberInventory) {
        this.numberInventory = numberInventory;
    }*/

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }
}
