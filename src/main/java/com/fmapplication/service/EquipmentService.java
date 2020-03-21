package com.fmapplication.service;

import com.fmapplication.entity.Equipment;
import com.fmapplication.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EquipmentService {

    public void save(Equipment equipment) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        session.save(equipment);
        trx.commit();
        session.close();
    }

    public void delete(Equipment equipment) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Equipment WHERE id=:id");
        query.setInteger("id", equipment.getId());
        query.executeUpdate();
        trx.commit();
        session.close();
    }

    public List<Equipment> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        Query query = session.createQuery("SELECT e FROM Equipment e");
        List<Equipment> equipmentList = query.list();
        trx.commit();
        session.close();
        return equipmentList;
    }

    public void updateAfterAccept(Equipment equipment) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("UPDATE Equipment SET "
                //+ "title=:title, "
               // + "description=:description, "
               // + "startTime=:startTime, "
               // + "endTime=:endTime, "
               // + "rate=:rate, "
                + "status=:status, "
               // + "createdBy=:createdBy, "
                + "acceptedBy=:acceptedBy WHERE id=:id");
      //  query.setParameter("title", equipment.getTitle());
      //  query.setParameter("description", equipment.getDescription());
      //  query.setParameter("startTime", equipment.getStartTime());
      //  query.setParameter("endTime", equipment.getEndTime());
      //  query.setParameter("rate", equipment.getRate());
        query.setParameter("status", equipment.getStatus());
       // query.setParameter("createdBy", equipment.getCreatedBy());
        query.setParameter("acceptedBy", equipment.getAcceptedBy());
        query.setParameter("id", equipment.getId());
        query.executeUpdate();
        tx1.commit();
        session.close();
    }

}
