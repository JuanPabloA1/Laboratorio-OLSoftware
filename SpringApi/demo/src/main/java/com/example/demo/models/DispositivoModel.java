package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity @Table(name = "dispositivos")
public class DispositivoModel extends RepresentationModel<DispositivoModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dispositivo")
    private Integer idDispositivo;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "area", referencedColumnName = "id_area")
    private AreaModel area;

    @Column(name = "estado_dispositivo")
    private String estadoDispositivo;

    @Column(name = "tipo_dispositivo")
    private String tipoDispositivo;

    @ManyToOne
    @JoinColumn(name = "fabricante", referencedColumnName = "id_fabricante")
    private FabricanteModel fabricante;

    @ManyToOne
    @JoinColumn(name = "modelo", insertable = false, updatable = false, referencedColumnName = "id_modelo")
    private ModeloModel modelo;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "numero_inventario")
    private int numeroInventario;

    private String comentario;

    private boolean borrado;

    /*@JsonCreator
    public DispositivoModel(
            @JsonProperty("idDispositivo") Integer idDispositivo, @JsonProperty("nombre") String nombre,
            @JsonProperty("area") AreaModel area, @JsonProperty("estadoDispositivo") String estadoDispositivo,
            @JsonProperty("tipoDispositivo") String tipoDispositivo, @JsonProperty("fabricante") FabricanteModel fabricante,
            @JsonProperty("modelo") ModeloModel modelo, @JsonProperty("numeroSerie") String numeroSerie,
            @JsonProperty("numeroInventario") String numeroInventario, @JsonProperty("comentario") String comentario) {
        this.idDispositivo = idDispositivo;
        this.nombre = nombre;
        this.area = area;
        this.estadoDispositivo = estadoDispositivo;
        this.tipoDispositivo = tipoDispositivo;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.numeroInventario = numeroInventario;
        this.comentario = comentario;
    }*/

    public DispositivoModel() {

    }


    public Integer getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Integer idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AreaModel getArea() {
        return area;
    }

    public void setArea(AreaModel area) {
        this.area = area;
    }

    public String getEstadoDispositivo() {
        return estadoDispositivo;
    }

    public void setEstadoDispositivo(String estadoDispositivo) {
        this.estadoDispositivo = estadoDispositivo;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public FabricanteModel getFabricante() {
        return fabricante;
    }

    public void setFabricante(FabricanteModel fabricante) {
        this.fabricante = fabricante;
    }

    public ModeloModel getModelo() {
        return modelo;
    }

    public void setModelo(ModeloModel modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getNumeroInventario() {
        return numeroInventario;
    }

    public void setNumeroInventario(Integer numeroInventario) {
        this.numeroInventario = numeroInventario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
}
