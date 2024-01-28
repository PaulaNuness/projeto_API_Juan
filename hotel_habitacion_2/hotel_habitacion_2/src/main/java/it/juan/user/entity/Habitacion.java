package it.juan.user.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "habitacion")
public class Habitacion {
    //	@Schema: Documenta un atributo, considerado como un campo de entrada (o salida)
    @Schema(description = "Identificador da habitacion", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /***********************************************/
    @ManyToOne
    @JoinColumn(name = "id_hotel", nullable = false)  // "nullable = false" indica que a associação é obrigatória
    private Hotel hotel;

    /***********************************************/
    @Schema(description = "Tamanho de la habitacion", example = "double", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private double tamanho;

    /***********************************************/
    @Schema(description = "Numero de pessoas por habitacion", example = "1 o 2", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private int numero_de_personas;

    /***********************************************/
    @Schema(description = "Precio noche da habitacion", example = "Double", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private double precio_noche;

    /***********************************************/
    @Schema(description = "Incluye desayuno", example = "True o False", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private Boolean incluye_desayuno;

    /***********************************************/
    @Schema(description = "Habitacion ocupada", example = "True o False", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private Boolean habitacion_ocupada;

    /***********************************************/

}
