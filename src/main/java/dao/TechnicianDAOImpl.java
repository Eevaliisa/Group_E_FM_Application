package dao;

import entity.Technician;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.Hibernate;

import java.util.List;

public class TechnicianDAOImpl implements TechnicianDAO {

    @Override
    public Technician getById(String id) {
        return null;
    }

    @Override
    public void addNewTechnician(Technician technician) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(technician);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteTechnician(Technician technician) {

    }

    @Override
    public void updateTechnician(Technician technician) {

    }

    @Override
    public List<Technician> getAllTechnicians() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            String hql = "FROM Technician";
            Query query = session.createQuery(hql);
            List results = query.list();
            return results;
        }
    }

    @Override
    public boolean isExistingTechnician(String id) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("FROM Technician t WHERE t.id= :id");
        query.setParameter("id", id);

        List<Technician> list = query.list();
        trx.commit();
        session.close();

        if (list.isEmpty()) {
            return false;
        }
        Technician technician = list.get(0);
        return true;
    }

}
