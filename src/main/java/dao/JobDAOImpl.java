package dao;

import entity.Job;
import org.hibernate.Session;
import util.Hibernate;

import javax.persistence.Query;
import java.util.List;

public class JobDAOImpl implements JobDAO {
    @Override
    public Job getByCategory(String category) {
        return null;
    }

    @Override
    public void addNewJob(Job job) {

    }

    @Override
    public void deleteJob(Job job) {

    }

    @Override
    public void updateJob(Job job) {

    }

    @Override
    public List<Job> getAllPendingJobs() {

        try (Session session = Hibernate.getSessionFactory().openSession()) {
            //Session session = Hibernate.getSessionFactory().openSession();
            String hql = "FROM Job j WHERE j.jobStatus = :status";
            Query query = session.createQuery(hql);
            query.setParameter("status", "pending");
            List pendingJobsList = query.getResultList();
            return pendingJobsList;
        }
    }

    @Override
    public List<Job> getAllAcceptedJobs() {
        return null;
    }
}
