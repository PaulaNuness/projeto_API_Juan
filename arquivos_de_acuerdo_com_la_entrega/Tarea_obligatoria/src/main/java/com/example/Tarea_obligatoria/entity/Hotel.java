package com.example.Tarea_obligatoria.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;
import java.util.List;



@Entity
@Table(name="hotel")
public class Hotel {

    //	@Schema: Documenta un atributo, considerado como un campo de entrada (o salida)
    @Schema(description = "Identificador do hotel", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /***********************************************/

    //	@Schema: Documenta un atributo, considerado como un campo de entrada (o salida)
    @Schema(description = "Nombre do hotel", example = "Mar e Sol", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private String nombre_hotel;

    /***********************************************/

    @Schema(description = "Descripcion del hotel", example = "Mejor hotel cerca del mar", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private String descripcion_hotel;

    /***********************************************/

    @Schema(description = "Categoria del hotel(Las estrellas)", example = "5", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private String categoria;

    /***********************************************/

    @Schema(description = "Tiene piscina", example = "True o False", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private Boolean tem_piscina;

    /***********************************************/

    @Schema(description = "Localidade del hotel(La ciudad)", example = "Madrid", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private String localidade;

    /***********************************************/
    @Schema(description = "Identificador de las habitaciones", example = "101", required = true)
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;

    /***********************************************/
    public Hotel(int id, String nombre_hotel, String descripcion_hotel, String categoria, Boolean tem_piscina, String localidade) {
        this.id = id;
        this.nombre_hotel = nombre_hotel;
        this.descripcion_hotel = descripcion_hotel;
        this.categoria = categoria;
        this.tem_piscina = tem_piscina;
        this.localidade = localidade;
    }

    /***********************************************/
    public Hotel() {
    }

    /***********************************************/
    public Hotel(int id, String nombre_hotel, String descripcion_hotel, String categoria, Boolean tem_piscina, String localidade, List<Habitacion> habitaciones) {
        this.id = id;
        this.nombre_hotel = nombre_hotel;
        this.descripcion_hotel = descripcion_hotel;
        this.categoria = categoria;
        this.tem_piscina = tem_piscina;
        this.localidade = localidade;
        this.habitaciones = habitaciones;
    }

    /***********************************************/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_hotel() {
        return nombre_hotel;
    }

    public void setNombre_hotel(String nombre_hotel) {
        this.nombre_hotel = nombre_hotel;
    }

    public String getDescripcion_hotel() {
        return descripcion_hotel;
    }

    public void setDescripcion_hotel(String descripcion_hotel) {
        this.descripcion_hotel = descripcion_hotel;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean getTem_piscina() {
        return tem_piscina;
    }

    public void setTem_piscina(Boolean tem_piscina) {
        this.tem_piscina = tem_piscina;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
    /***********************************************/
    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", nombre_hotel='" + nombre_hotel + '\'' +
                ", descripcion_hotel='" + descripcion_hotel + '\'' +
                ", categoria='" + categoria + '\'' +
                ", tem_piscina=" + tem_piscina +
                ", localidade='" + localidade + '\'' +
                ", habitaciones=" + habitaciones +
                '}';
    }

    /***********************************************/
}

