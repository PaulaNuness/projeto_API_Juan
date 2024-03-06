package com.example.Tarea_obligatoria.controller;

import com.example.Tarea_obligatoria.entity.Habitacion;
import com.example.Tarea_obligatoria.exception.ProductNotFoundException;
import com.example.Tarea_obligatoria.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.Tarea_obligatoria.controller.Response.NOT_FOUND;

@RestController
//@Tag: Permite documentar el controlador
@Tag(name = "Habitaciones", description = "Catálogo de habitaciones")
@RequestMapping("/api") //esta sera a raiz, para fazer as consultas

public class HabitacionRestController {

    private final Logger logger = LoggerFactory.getLogger(HotelRestController.class);

    @Autowired
    private HotelService habitacionService;


    /*****************************************************************************************************************/

    //	@Operation: Permite definir una descripción para la operación
    @Operation(summary = "Obtiene el listado de habitaciones")
    //	@ApiResponses: Permite documentar la forma en que una operación concreta responde,
    // teniendo en cuenta las posibles respuestas en caso de error
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de habitaciones",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Habitacion.class)))),
    })
    @GetMapping("/buscar_todas_as habitaciones")
    public List<Habitacion> buscar_todas_as_habitaciones(){
        return habitacionService.buscar_todas_habitaciones();
    }

    /**********************************************/

    @Operation(summary = "Obtiene una habitacion determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe habitacion", content = @Content(schema = @Schema(implementation = Habitacion.class))),
            @ApiResponse(responseCode = "404", description = "Habitacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/buscar_habitacion_por_id/{userId}")
    public Habitacion getHabitacion(@PathVariable int userId){
        Habitacion habitacion = habitacionService.buscar_habitacion_por_id(userId);

        if(habitacion == null) {
            throw new RuntimeException("User id not found -"+userId);
        }
        return habitacion;
    }

    /**********************************************/

    @Operation(summary = "Registra una habitacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra una habitacion", content = @Content(schema = @Schema(implementation = Habitacion.class)))
    })
    @PostMapping("/crear_habitacion")
    public Habitacion addHabitacion(@RequestBody Habitacion user) {

        user.setId(0);

        habitacionService.crear_ou_actualizar_habitacion(user);

        return user;

    }

    /**********************************************/

    @Operation(summary = "Modifica una habitacion en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica habitacion", content = @Content(schema = @Schema(implementation = Habitacion.class))),
            @ApiResponse(responseCode = "404", description = "Habitacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping("/actualizar_habitacion")
    public Habitacion updateHabitacion(@RequestBody Habitacion user) {

        habitacionService.crear_ou_actualizar_habitacion(user);


        return user;
    }

    /**********************************************/

    @Operation(summary = "Elimina una habitacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la habitacion", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Habitacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("deletar_habitacion/{userId}")
    public String deteteHabitacion(@PathVariable int userId) {

        Habitacion habitacion = habitacionService.buscar_habitacion_por_id(userId);

        if(habitacion == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        habitacionService.delete_habitacion(userId);


        return "Deleted user id - "+userId;
    }

    /**********************************************/


    @Operation(summary = "Obtiene habitaciones por tamaño y precio si estan libres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe habitacion", content = @Content(schema = @Schema(implementation = Habitacion.class))),
            @ApiResponse(responseCode = "404", description = "Habitacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/buscar_habitaciones_por_tamanho_y_precio_se_esta_libre")
    public List<Habitacion> buscarPorTamanhoYPrecioCuandoEstanLibres(@RequestParam double tamanhoMinimo, @RequestParam double tamanhoMaximo, @RequestParam double precioMinimo, @RequestParam double precioMaximo) {

        List<Habitacion> habitaciones = habitacionService.buscar_Habitaciones_Por_Tamanho_Y_Precio_cuando_estan_libres(tamanhoMinimo, tamanhoMaximo, precioMinimo, precioMaximo);

        return habitaciones;
    }

    /**********************************************/

    @Operation(summary = "Poner una habitacion como ocupada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica habitacion", content = @Content(schema = @Schema(implementation = Habitacion.class))),
            @ApiResponse(responseCode = "404", description = "Habitacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping("/actualizar_ocupacion_de_la_habitacion/{Id}")
    public void updateUser(@PathVariable int Id) {

        habitacionService.mudar_habitacion_para_ocupada(Id);

        //este metodo actualizará al usuario enviado

    }
    /**********************************************/

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ProductNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    /**********************************************/
}
