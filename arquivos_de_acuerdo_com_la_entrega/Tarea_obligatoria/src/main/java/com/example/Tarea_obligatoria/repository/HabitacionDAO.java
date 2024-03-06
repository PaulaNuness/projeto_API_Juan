package com.example.Tarea_obligatoria.repository;

import com.example.Tarea_obligatoria.entity.Habitacion;

import java.util.List;

public interface HabitacionDAO {
    public List<Habitacion> buscar_todas_habitaciones();

    public Habitacion buscar_habitacion_por_id(int id);

    public void crear_ou_actualizar_habitacion(Habitacion habitacion);

    public void deletar_habitacion(int id);
    public List<Habitacion> buscar_Habitaciones_Por_Tamanho_Y_Precio_cuando_estan_libres(double tamanhoMinimo, double tamanhoMaximo, double precioMinimo, double precioMaximo);

    public Habitacion mudar_habitacion_para_ocupada(int id);
}
