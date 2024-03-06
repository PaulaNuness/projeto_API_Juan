package com.example.Tarea_obligatoria.repository;

import com.example.Tarea_obligatoria.entity.Habitacion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class HabitacionDAOImpl implements HabitacionDAO{

    @Autowired
    private EntityManager entityManager;

    /**************************************/

    @Override
    public List<Habitacion> buscar_todas_habitaciones() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Habitacion> theQuery = currentSession.createQuery("SELECT u from Habitacion  u", Habitacion.class);

        List<Habitacion> habitacions = theQuery.getResultList();

        return habitacions;
    }

    /**************************************/
    @Override
    public Habitacion buscar_habitacion_por_id(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Habitacion habitacion = currentSession.get(Habitacion.class, id);

        return habitacion;
    }

    /**************************************/
    @Override
    public void crear_ou_actualizar_habitacion(Habitacion habitacion) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();
        currentSession.saveOrUpdate(habitacion);
        t.commit();
        currentSession.close();
    }

    /**************************************/
    @Override
    public void deletar_habitacion(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();

        Query theQuery = currentSession.createQuery("delete from Habitacion u where id IN (:idUser)");

        theQuery.setParameter("idUser", id);
        theQuery.executeUpdate();
        t.commit();
        currentSession.close();
    }

    /**************************************/

    @Override
    public List<Habitacion> buscar_Habitaciones_Por_Tamanho_Y_Precio_cuando_estan_libres(double tamanhoMinimo, double tamanhoMaximo, double precioMinimo, double precioMaximo) {
        Session currentSession = entityManager.unwrap(Session.class);

        String hql = "FROM Habitacion h WHERE h.tamanho BETWEEN :tamanhoMinimo AND :tamanhoMaximo " +
                "AND h.precio_noche BETWEEN :precioMinimo AND :precioMaximo AND h.habitacion_ocupada = false ";

        Query<Habitacion> query = currentSession.createQuery(hql, Habitacion.class);
        query.setParameter("tamanhoMinimo", tamanhoMinimo);
        query.setParameter("tamanhoMaximo", tamanhoMaximo);
        query.setParameter("precioMinimo", precioMinimo);
        query.setParameter("precioMaximo", precioMaximo);

        return query.getResultList();
    }
    /**************************************/

    @Override
    public Habitacion mudar_habitacion_para_ocupada(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Habitacion habitacion = currentSession.get(Habitacion.class, id);
        Transaction t = currentSession.beginTransaction();

        if (habitacion != null) {
            habitacion.setHabitacion_ocupada(true);
            currentSession.saveOrUpdate(habitacion);
            t.commit();
            currentSession.close();
            return habitacion;
        } else {
            throw new EntityNotFoundException("Habitacion não encontrada com o ID: " + id);
        }
    }
    /**************************************/
}
