package it.juan.user.dao;

import it.juan.user.entity.Hotel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class HotelDAOImpl implements HotelDAO {

    @Autowired
    private EntityManager entityManager;

    /**************************************/
    @Override
    public List<Hotel> buscar_todos_hoteis() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Hotel> theQuery = currentSession.createQuery("SELECT u from hotel u", Hotel.class);

        List<Hotel> hoteis = theQuery.getResultList();

        return hoteis;
    }

    /**************************************/
    @Override
    public Hotel buscar_hotel_por_id(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Hotel hotel = currentSession.get(Hotel.class, id);

        return hotel;
    }

    /**************************************/
    @Override
    public void crear_ou_actualizar_hotel(Hotel hotel) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();
        currentSession.saveOrUpdate(hotel);
        t.commit();
        currentSession.close();
    }
    /**************************************/
    @Override
    public void deletar_hotel(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction transaction = currentSession.beginTransaction();

        try {
            Hotel hotel = currentSession.get(Hotel.class, id);

            // Verifique se o hotel existe
            if (hotel != null) {
                // As habitaciones associadas serão excluídas devido à configuração de cascata
                currentSession.delete(hotel);
            } else {
                // Lide com o caso em que o hotel com o ID especificado não é encontrado
                throw new EntityNotFoundException("Hotel with ID " + id + " not found");
            }

            transaction.commit();
        } catch (Exception e) {
            // Lide com exceções, registre ou relance se necessário
            transaction.rollback();
            throw e;
        } finally {
            currentSession.close();
        }
    }

    /**************************************/
    @Override
    public List<Hotel> buscar_por_categoria(String category) {
        Session currentSession = entityManager.unwrap(Session.class);

        // Use uma consulta HQL (Hibernate Query Language) para obter hotéis com a categoria especificada
        Query<Hotel> theQuery = currentSession.createQuery("SELECT h FROM hotel h WHERE h.categoria = :category", Hotel.class);
        theQuery.setParameter("category", category);

        List<Hotel> hotels = theQuery.getResultList();

        return hotels;
    }

    /**************************************/
    @Override
    public List<Hotel> buscar_por_localidade(String local) {
        Session currentSession = entityManager.unwrap(Session.class);

        // Use uma consulta HQL (Hibernate Query Language) para obter hotéis com a categoria especificada
        Query<Hotel> theQuery = currentSession.createQuery("SELECT h FROM hotel h WHERE h.localidade = :local", Hotel.class);
        theQuery.setParameter("local", local);

        List<Hotel> hotels = theQuery.getResultList();

        return hotels;
    }

    /**************************************/
    @Override
    public long cuantos_hoteis_tem_piscina() {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();

        Query<Long> query = currentSession.createQuery("SELECT COUNT(h) FROM hotel h WHERE h.tem_piscina = true", Long.class);
        long count = query.uniqueResult();

        t.commit();
        currentSession.close();

        return count;
    }

    /**************************************/
    @Override
    public long cuantos_hoteis_tem_essa_localidade(String local) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction t = currentSession.beginTransaction();

        Query<Long> query = currentSession.createQuery("SELECT COUNT(h) FROM hotel h WHERE h.localidade = :local", Long.class);
        query.setParameter("local", local);  // Defina o valor do parâmetro local
        long count = query.uniqueResult();

        t.commit();
        currentSession.close();

        return count;
    }
    /**************************************/
}