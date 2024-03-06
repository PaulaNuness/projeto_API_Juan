package com.example.Tarea_obligatoria.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;


@Entity
@Table(name="habitacion")
public class Habitacion {
    //	@Schema: Documenta un atributo, considerado como un campo de entrada (o salida)
    @Schema(description = "Identificador da habitacion", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /***********************************************/
    @Schema(description = "Identificador del hotel", example = "1", required = true)
    @ManyToOne
    @JoinColumn(name = "id_hotel", nullable = false)  // "nullable = false" indica que a associação é obrigatória
    @JsonIgnore
    private Hotel hotel;

    /***********************************************/
    @Schema(description = "Tamaño de la habitacion(metros cuadrados)", example = "20.0", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private double tamanho;

    /***********************************************/
    @Schema(description = "Numero de pessoas por habitacion", example = "1 o 2", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private int numero_de_personas;

    /***********************************************/
    @Schema(description = "Precio noche da habitacion", example = "100.8", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private double precio_noche;

    /***********************************************/
    @Schema(description = "Incluye desayuno", example = "True o False", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private Boolean incluye_desayuno;

    /***********************************************/
    @Schema(description = "Habitacion ocupada", example = "True o False", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    //@NotBlank
    @Column
    private Boolean habitacion_ocupada;

    /***********************************************/
    public Habitacion() {
    }
    /***********************************************/
    public Habitacion(int id, Hotel hotel, double tamanho, int numero_de_personas, double precio_noche, Boolean incluye_desayuno, Boolean habitacion_ocupada) {
        this.id = id;
        this.hotel = hotel;
        this.tamanho = tamanho;
        this.numero_de_personas = numero_de_personas;
        this.precio_noche = precio_noche;
        this.incluye_desayuno = incluye_desayuno;
        this.habitacion_ocupada = habitacion_ocupada;
    }
    /***********************************************/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    //PARA QUE ME MUESTRE EL IDHOTEL AL BUSCAR UNA HABITACION
    @JsonProperty("idHotel")
    public int getIdHotel() {
        return hotel.getId();
    }
    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public int getNumero_de_personas() {
        return numero_de_personas;
    }

    public void setNumero_de_personas(int numero_de_personas) {
        this.numero_de_personas = numero_de_personas;
    }

    public double getPrecio_noche() {
        return precio_noche;
    }

    public void setPrecio_noche(double precio_noche) {
        this.precio_noche = precio_noche;
    }

    public Boolean getIncluye_desayuno() {
        return incluye_desayuno;
    }

    public void setIncluye_desayuno(Boolean incluye_desayuno) {
        this.incluye_desayuno = incluye_desayuno;
    }

    public Boolean getHabitacion_ocupada() {
        return habitacion_ocupada;
    }

    public void setHabitacion_ocupada(Boolean habitacion_ocupada) {
        this.habitacion_ocupada = habitacion_ocupada;
    }
    /***********************************************/
    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", tamanho=" + tamanho +
                ", numero_de_personas=" + numero_de_personas +
                ", precio_noche=" + precio_noche +
                ", incluye_desayuno=" + incluye_desayuno +
                ", habitacion_ocupada=" + habitacion_ocupada +
                '}';
    }

    /***********************************************/
}
