package com.example.Tarea_obligatoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.	Crea una API que ofrezca servicios web de búsqueda de hoteles. Se mantendrá un base de datos de hoteles (nombre, descripción, categoría, ¿piscina?, localidad) y de las habitaciones de los mismos (tamaño, 1 ó 2 personas, precio/noche, ¿incluye desayuno?, ¿ocupada?). Deberá ofrecer, sobre esos datos, las siguientes operaciones:
 * a.	Búsqueda de hotel por localidad o categoría
 * b.	Búsqueda de habitaciones de un hotel por tamaño y precio (rango minimo→máximo). Solo mostrará aquellas habitaciones que estén marcadas como libres
 * c.	Registrar una nueva habitación a un hotel
 * d.	Eliminar una habitación determinada de un hotel
 * e.	Modificar una habitación para indicar que está ocupada
 */

@SpringBootApplication
public class TareaObligatoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TareaObligatoriaApplication.class, args);
	}

}
