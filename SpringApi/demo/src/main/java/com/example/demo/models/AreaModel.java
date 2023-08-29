package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "areas")
public class AreaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area", unique = true, nullable = false)
    private Integer idArea;

    private String nombre;

    @OneToMany(mappedBy = "area")
    private List<DispositivoModel> dispositivos;

    @OneToMany(mappedBy = "area")
    private List<UsuarioModel> usuarios;

    private boolean borrado;

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
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

    public List<UsuarioModel> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioModel> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
}
