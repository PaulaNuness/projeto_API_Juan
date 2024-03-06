package com.example.Tarea_obligatoria.service;

import com.example.Tarea_obligatoria.entity.Hotel;
import com.example.Tarea_obligatoria.entity.Habitacion;

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
    public long cuantas_habitaciones_en_el_hotel(int id_hotel);


    /****************************************************************************/

    public List<Habitacion> buscar_todas_habitaciones();

    public Habitacion buscar_habitacion_por_id(int id);

    public void crear_ou_actualizar_habitacion(Habitacion habitacion);

    public void delete_habitacion(int id);

    public List<Habitacion> buscar_Habitaciones_Por_Tamanho_Y_Precio_cuando_estan_libres(double tamanhoMinimo, double tamanhoMaximo, double precioMinimo, double precioMaximo);

    public Habitacion mudar_habitacion_para_ocupada(int id);

}
