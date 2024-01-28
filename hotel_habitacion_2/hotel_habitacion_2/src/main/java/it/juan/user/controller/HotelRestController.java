package it.juan.user.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.juan.user.entity.Habitacion;
import it.juan.user.entity.Hotel;
import it.juan.user.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@Tag: Permite documentar el controlador
@Tag(name = "Hoteis/Habitaciones", description = "Catálogo de hoteis")


public class HotelRestController {


    private final Logger logger = LoggerFactory.getLogger(HotelRestController.class);

    @Autowired
    private HotelService hotelService;


    /*****************************************************************************************************************/

    //	@Operation: Permite definir una descripción para la operación
    @Operation(summary = "Obtiene el listado de hoteles")
    //	@ApiResponses: Permite documentar la forma en que una operación concreta responde,
    // teniendo en cuenta las posibles respuestas en caso de error
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de hoteles",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hotel.class)))),
    })
    @GetMapping("/buscar_todos_os hoteis")
    public List<Hotel> buscar_todos_os_hoteis(){
        return hotelService.buscar_todos_hoteis();
    }
    /********************************************************************************************************************/

    @Operation(summary = "Obtiene un hotel determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el hotel", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "El hotel no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/buscar_hotel_por_id/{userId}")
    public Hotel getHotel(@PathVariable int userId){
        Hotel hotel = hotelService.buscar_hotel_por_id(userId);

        if(hotel == null) {
            throw new RuntimeException("User id not found -"+userId);
        }
        return hotel;
    }
    /************************************************************************************************************************/

    @Operation(summary = "Registra un nuevo hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el hotel", content = @Content(schema = @Schema(implementation = Hotel.class)))
    })
    @PostMapping("/crear_hotel")
    public Hotel addHotel(@RequestBody Hotel user) {
        user.setId(0);

        hotelService.crear_ou_actualizar_hotel(user);

        return user;

    }
    /*************************************************************************************************************************/

    @Operation(summary = "Modifica un hotel en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el hotel", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "El hotel no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping("/actualizar_hotel")
    public Hotel updateHotel(@RequestBody Hotel user) {

        hotelService.crear_ou_actualizar_hotel(user);

        //este metodo actualizará al usuario enviado

        return user;
    }
    /***********************************************************************************************************************/

    @Operation(summary = "Elimina un hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el producto", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("deletar_hotel/{userId}")
    public String deteteHotel(@PathVariable int userId) {

        Hotel user = hotelService.buscar_hotel_por_id(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        hotelService.delete_hotel(userId);

        return "Deleted user id - "+userId;
    }

    /**********************************************************************************************************************/
    @Operation(summary = "Obtiene una lista de hoteis por categoria em concreto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron hoteles para la categoría especificada", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("buscar_hotel_por_categoria/{category}")
    public List<Hotel> buscar_por_categoria(@PathVariable String category) {
        return hotelService.buscar_hotel_por_categoria(category);
    }
    /***********************************************************************************************************************/
    @Operation(summary = "Obtiene una lista de hoteis por una localidade em concreto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hoteles encontrados", content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron hoteles para la localidade especificada", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("buscar_hotel_por_localidade/{local}")
    public List<Hotel> buscar_por_localidade(@PathVariable String local) {
        return hotelService.buscar_hotel_por_localidade(local);
    }
    /********************************************************************************************************************/

    @Operation(summary = "Obtiene la cantidad de hoteles con piscina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Número de hoteles con piscina obtenido con éxito", content = @Content(schema = @Schema(implementation = Long.class)))
    })
    @GetMapping("cuantos_hoteles_com_piscina")
    public ResponseEntity<Long> cuantos_hoteis_tem_piscina() {
        long count = hotelService.cuantos_hoteis_tem_piscina();
        return ResponseEntity.ok(count);
    }

    /**********************************************************************************************************************/

    @Operation(summary = "Obtiene la cantidad de hoteles con una localidad específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Número de hoteles con la localidad especificada obtenido con éxito", content = @Content(schema = @Schema(implementation = Long.class)))
    })
    @GetMapping("/cuantos_hoteles_com_essa_localidade/{local}")
    public ResponseEntity<Long> cuantos_hoteis_tem_essa_localidade(@PathVariable String local) {
        long count = hotelService.cuantos_hoteis_tem_essa_localidade(local);
        return ResponseEntity.ok(count);
    }
    /*********************************************************************************************************************/

     /*                  HABITACION             */

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
        return hotelService.buscar_todas_habitaciones();
    }
    /**********************************************/

    @Operation(summary = "Obtiene una habitacion determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe habitacion", content = @Content(schema = @Schema(implementation = Habitacion.class))),
            @ApiResponse(responseCode = "404", description = "Habitacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/buscar_habitacion_por_id/{userId}")
    public Habitacion getHabitacion(@PathVariable int userId){
        Habitacion habitacion = hotelService.buscar_habitacion_por_id(userId);

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

        hotelService.crear_ou_actualizar_habitacion(user);

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

        hotelService.crear_ou_actualizar_habitacion(user);


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

        Habitacion habitacion = hotelService.buscar_habitacion_por_id(userId);

        if(habitacion == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        hotelService.delete_habitacion(userId);


        return "Deleted user id - "+userId;
    }
    /**********************************************/

}
