package com.example.Tarea_obligatoria.repository;

import com.example.Tarea_obligatoria.entity.Hotel;

import java.util.List;


public interface HotelDAO {

    public List<Hotel> buscar_todos_hoteis();

    public Hotel buscar_hotel_por_id(int id);

    public void crear_ou_actualizar_hotel(Hotel hotel);

    public void deletar_hotel(int id);

    public List<Hotel> buscar_por_categoria(String category);
    public List<Hotel> buscar_por_localidade(String local);
    public long cuantos_hoteis_tem_piscina();

    public long cuantos_hoteis_tem_essa_localidade(String local);
    public long cuantas_habitaciones_en_el_hotel(int id_hotel);

}

