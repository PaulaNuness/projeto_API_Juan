package com.example.Tarea_obligatoria.service;


import com.example.Tarea_obligatoria.repository.HotelDAO;
import com.example.Tarea_obligatoria.repository.HabitacionDAO;
import com.example.Tarea_obligatoria.entity.Hotel;
import com.example.Tarea_obligatoria.entity.Habitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;


    @Autowired
    private HabitacionDAO habitacionDAO;

    /***********************************/

    @Override
    public List<Hotel> buscar_todos_hoteis() {
        List<Hotel> listHoteles= hotelDAO.buscar_todos_hoteis();
        return listHoteles;
    }

    /***********************************/
    @Override
    public Hotel buscar_hotel_por_id(int id) {
        Hotel hotel = hotelDAO.buscar_hotel_por_id(id);
        return hotel;
    }

    /***********************************/
    @Override
    public void crear_ou_actualizar_hotel(Hotel hotel) {
        hotelDAO.crear_ou_actualizar_hotel(hotel);
    }

    /***********************************/
    @Override
    public void delete_hotel(int id) {
        hotelDAO.deletar_hotel(id);
    }

    /***********************************/
    @Override
    public List<Hotel> buscar_hotel_por_categoria(String category) {
        return hotelDAO.buscar_por_categoria(category);
    }

    /***********************************/
    @Override
    public List<Hotel> buscar_hotel_por_localidade(String local) {
        return hotelDAO.buscar_por_localidade(local);
    }

    /***********************************/
    @Override
    public long cuantos_hoteis_tem_piscina() {
        return hotelDAO.cuantos_hoteis_tem_piscina();
    }

    /***********************************/
    @Override
    public long cuantos_hoteis_tem_essa_localidade(String local) {
        return hotelDAO.cuantos_hoteis_tem_essa_localidade(local);
    }

    /***********************************/
    @Override
    public long cuantas_habitaciones_en_el_hotel(int id_hotel) {
        return hotelDAO.cuantas_habitaciones_en_el_hotel(id_hotel);
    }

    /****************************************************************************************************************************************/

    @Override
    public List<Habitacion> buscar_todas_habitaciones() {
        List<Habitacion> listHabitacions = habitacionDAO.buscar_todas_habitaciones();
        return listHabitacions;
    }

    /***********************************/
    @Override
    public Habitacion buscar_habitacion_por_id(int id) {
        Habitacion habitacion = habitacionDAO.buscar_habitacion_por_id(id);
        return habitacion;
    }

    /***********************************/
    @Override
    public void crear_ou_actualizar_habitacion(Habitacion habitacion) {
        habitacionDAO.crear_ou_actualizar_habitacion(habitacion);
    }

    /***********************************/
    @Override
    public void delete_habitacion(int id) {
        habitacionDAO.deletar_habitacion(id);
    }

    /***********************************/
    @Override
    public List<Habitacion> buscar_Habitaciones_Por_Tamanho_Y_Precio_cuando_estan_libres(double tamanhoMinimo, double tamanhoMaximo, double precioMinimo, double precioMaximo) {
        return habitacionDAO.buscar_Habitaciones_Por_Tamanho_Y_Precio_cuando_estan_libres(tamanhoMinimo, tamanhoMaximo, precioMinimo, precioMaximo);
    }

    /***********************************/

    @Override
    public Habitacion mudar_habitacion_para_ocupada(int id) {
        Habitacion habitacion = habitacionDAO.mudar_habitacion_para_ocupada(id);
        return habitacion;
    }
    /***********************************/



}
