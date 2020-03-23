package dao;

import entity.Technician;

import java.util.List;

public interface TechnicianDAO {

    Technician getById(String id);

    void addNewTechnician(Technician technician);

    void deleteTechnician(Technician technician);

    void updateTechnician(Technician technician);

    public List<Technician> getAllTechnicians();
}
