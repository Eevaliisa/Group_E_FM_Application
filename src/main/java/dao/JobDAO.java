package dao;

import entity.Job;

import java.util.List;

public interface JobDAO {

    Job getByCategory(String category);

    void addNewJob(Job job);

    void deleteJob(Job job);

    void updateJob(Job job);

    public List<Job> getAllPendingJobs();

    public List<Job> getAllAcceptedJobs();

    public List<Job> getAllInProgressJobs();

    public List<Job> getAllJobsByStatus(String status);

    public List<Job> getAllJobsByEquipment(String equipment);

    public List<Job> getAllJobsByCategory(String category);


    Job getJobByID(int id);





}

