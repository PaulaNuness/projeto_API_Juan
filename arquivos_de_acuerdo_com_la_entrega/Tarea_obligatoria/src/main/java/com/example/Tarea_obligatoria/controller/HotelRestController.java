package com.example.Tarea_obligatoria.controller;


import com.example.Tarea_obligatoria.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.Tarea_obligatoria.entity.Hotel;
import com.example.Tarea_obligatoria.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
//@Tag: Permite documentar el controlador
@Tag(name = "Hoteis", description = "Catálogo de hoteis")
@RequestMapping("/api") //esta sera a raiz, para fazer as consultas


public class HotelRestController {

    @Autowired
    private HotelService hotelService;

    /*****************************************************************************************************************/

    //configuracao para ter o token

    @PostMapping("user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {//o que devo colocar no metodo post

        if ((username.equals("juan")) && (pwd.equals("juan"))) {
            System.out.println("Me crea el token");
            String token = getJWTToken(username);
            User user = new User();
            user.setUser(username);
            user.setPwd(pwd);
            user.setToken(token);

            return user;
        } else

            return null;

    }


    //Utilizamos el método getJWTToken(...) para construir el token,
    // delegando en la clase de utilidad Jwts que incluye información sobre su expiración
    // y un objeto de GrantedAuthority de Spring que, como veremos más adelante,
    // usaremos para autorizar las peticiones a los recursos protegidos.

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }


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

    @Operation(summary = "Obtiene la cantidad de habitaciones de un hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Número de hoteles con piscina obtenido con éxito", content = @Content(schema = @Schema(implementation = Long.class)))
    })
    @GetMapping("cuantos_habitaciones_en_el_hotel/{id_hotel}")
    public ResponseEntity<Long> cuantos_habitaciones_en_el_hotel(@PathVariable int id_hotel) {
        long count = hotelService.cuantas_habitaciones_en_el_hotel(id_hotel);
        return ResponseEntity.ok(count);
    }

    /**********************************************************************************************************************/


}
