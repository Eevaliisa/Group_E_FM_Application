package dao;

import entity.Job;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.Hibernate;


import java.util.List;

public class JobDAOImpl implements JobDAO {
    @Override
    public Job getByCategory(String category) {
        return null;
    }

    @Override
    public void addNewJob(Job job) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        session.save(job);
        trx.commit();
        session.close();
    }

    @Override
    public void deleteJob(Job job) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        session.delete(job);
        trx.commit();
        session.close();
    }

    @Override
    public void updateJob(Job job) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        session.update(job);
        trx.commit();
        session.close();

    }

    @Override
    public Job getJobByID(int id) {
        Job job = null;
        Session session = Hibernate.getSessionFactory().openSession();
        job = (Job) session.get(Job.class, id);
        session.close();
        return job;
    }

    @Override
    public List<Job> getAllJobsByStatus(String status) {

        try (Session session = Hibernate.getSessionFactory().openSession()) {
            String hql = "FROM Job j WHERE j.jobStatus = :status";
            Query query = session.createQuery(hql);
            query.setParameter("status", status);
            List pendingJobsList = query.getResultList();
            return pendingJobsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Job> getAllJobsByEquipment(String equipment) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            String hql = "FROM Job j WHERE j.equipmentName = :equipment";
            Query query = session.createQuery(hql);
            query.setParameter("equipment", equipment);
            List pendingJobsList = query.getResultList();
            return pendingJobsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Job> getAllJobsByCategory(String category) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            String hql = "FROM Job j WHERE j.category = :category";
            Query query = session.createQuery(hql);
            query.setParameter("category", category);
            List pendingJobsList = query.getResultList();
            return pendingJobsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Job> getAllPendingJobs() {

        try (Session session = Hibernate.getSessionFactory().openSession()) {
            String hql = "FROM Job j WHERE j.jobStatus = :status";
            Query query = session.createQuery(hql);
            query.setParameter("status", "pending");
            List pendingJobsList = query.getResultList();
            return pendingJobsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Job> gettAllJobs() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            String hql = "SELECT j FROM Job j";
            Query query = session.createQuery(hql);
            List listJobs = query.getResultList();
            return listJobs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Job> getAllAcceptedJobs() {
        return null;
    }

    @Override
    public List<Job> getAllInProgressJobs() {

        try (Session session = Hibernate.getSessionFactory().openSession()) {
            String hql = "FROM Job j WHERE j.jobStatus = :status";
            Query query = session.createQuery(hql);
            query.setParameter("status", "in progress");
            List pendingJobsList = query.getResultList();
            return pendingJobsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
