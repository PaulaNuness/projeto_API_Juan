package it.juan.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hotel")
public class Hotel {

    //	@Schema: Documenta un atributo, considerado como un campo de entrada (o salida)
    @Schema(description = "Identificador do hotel", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /***********************************************/

    @Schema(description = "Nombre do hotel", example = "Mar e Sol", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private String nombre_hotel;

    /***********************************************/

    @Schema(description = "Descrpcion do hotel", example = "Mejor hotel ceca do mar", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private String descripcion_hotel;

    /***********************************************/

    @Schema(description = "Categoria do hotel", example = "Estrelas", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private String categoria;

    /***********************************************/

    @Schema(description = "Tiene piscina", example = "True o False", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private Boolean tem_piscina;

    /***********************************************/

    @Schema(description = "Localidade do hotel", example = "Ciudad", required = true)
    //	@NotBlank: Documenta que el atributo es obligatorio
    @NotBlank
    @Column
    private String localidade;

    /***********************************************/
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;

    /***********************************************/

}

