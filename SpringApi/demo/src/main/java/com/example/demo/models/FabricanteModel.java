package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity @Table(name = "fabricantes")
public class FabricanteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fabricante")
    private Integer idFabricante;

    private String nombre;

    @OneToMany(mappedBy = "fabricante")
    private List<DispositivoModel> dispositivos;

    @OneToMany(mappedBy = "fabricante")
    private List<ModeloModel> modelos;

    private boolean borrado;

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DispositivoModel> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<DispositivoModel> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public List<ModeloModel> getModelos() {
        return modelos;
    }

    public void setModelos(List<ModeloModel> modelos) {
        this.modelos = modelos;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
}
