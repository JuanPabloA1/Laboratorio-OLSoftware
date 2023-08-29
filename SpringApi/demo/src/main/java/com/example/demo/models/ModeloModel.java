package com.example.demo.models;

import javax.persistence.*;

@Entity @Table(name = "modelos")
public class ModeloModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private Integer idModelo;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "fabricante", referencedColumnName = "id_fabricante")
    private FabricanteModel fabricante;

    private boolean borrado;

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public FabricanteModel getFabricante() {
        return fabricante;
    }

    public void setFabricante(FabricanteModel fabricante) {
        this.fabricante = fabricante;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
}
