package it.juan.user.dao;

import it.juan.user.entity.Habitacion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HabitacionDAOImpl implements HabitacionDAO{

    @Autowired
    private EntityManager entityManager;

    /**************************************/

    @Override
    public List<Habitacion> buscar_todas_habitaciones() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Habitacion> theQuery = currentSession.createQuery("SELECT u from habitacion u", Habitacion.class);

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

        Query theQuery = currentSession.createQuery("delete from habitacion u where id IN (:idUser)");

        theQuery.setParameter("idUser", id);
        theQuery.executeUpdate();
        t.commit();
        currentSession.close();
    }

    /**************************************/
}
