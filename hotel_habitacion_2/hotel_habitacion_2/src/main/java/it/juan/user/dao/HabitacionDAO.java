package it.juan.user.dao;

import it.juan.user.entity.Habitacion;

import java.util.List;

public interface HabitacionDAO {
    public List<Habitacion> buscar_todas_habitaciones();

    public Habitacion buscar_habitacion_por_id(int id);

    public void crear_ou_actualizar_habitacion(Habitacion habitacion);

    public void deletar_habitacion(int id);
}
