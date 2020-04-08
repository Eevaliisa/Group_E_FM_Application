package dao;

import entity.Technician;
import org.hibernate.Session;
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
}
