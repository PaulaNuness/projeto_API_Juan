package it.juan.user.service;


import it.juan.user.dao.HabitacionDAO;
import it.juan.user.dao.HotelDAO;
import it.juan.user.entity.Habitacion;
import it.juan.user.entity.Hotel;
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



}
