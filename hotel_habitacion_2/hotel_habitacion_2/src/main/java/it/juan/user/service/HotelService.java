package it.juan.user.service;

import it.juan.user.entity.Habitacion;
import it.juan.user.entity.Hotel;

import java.util.List;


public interface HotelService {

    public List<Hotel> buscar_todos_hoteis();

    public Hotel buscar_hotel_por_id(int id);

    public void crear_ou_actualizar_hotel(Hotel hotel);

    public void delete_hotel(int id);

    public List<Hotel> buscar_hotel_por_categoria(String category);
    public List<Hotel> buscar_hotel_por_localidade(String local);
    public long cuantos_hoteis_tem_piscina();
    public long cuantos_hoteis_tem_essa_localidade(String local);


    /****************************************************************************/

    public List<Habitacion> buscar_todas_habitaciones();

    public Habitacion buscar_habitacion_por_id(int id);

    public void crear_ou_actualizar_habitacion(Habitacion habitacion);

    public void delete_habitacion(int id);




}
