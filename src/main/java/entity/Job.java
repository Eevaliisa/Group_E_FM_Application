package entity;

import javax.persistence.*;

@Entity
@Table(schema = "a0099663_FM_Application", name = "job")

public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobID")
    private int jobID;

    @Column(name = "category")
    private String category;

    @Column(name = "equipmentName")
    private String equipmentName;

    @Column(name = "jobLocation")
    private String jobLocation;

    @Column(name = "jobDescription")
    private String jobDescription;

    @Column(name = "jobStatus")
    private String jobStatus;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "technicianID")
    private String technicianID;

    @ManyToOne
    private Technician technician;

    private boolean isUpdated = false;

    public Job() {
    }

    public Job(
            int jobID,
            String category,
            String equipmentName,
            String jobLocation,
            String jobDescription,
            String jobStatus,
            boolean accepted) {
        this.jobID = jobID;
        this.category = category;
        this.equipmentName = equipmentName;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.jobStatus = jobStatus;
        this.accepted = accepted;
    }

    public Job(String category, String equipmentName, String jobLocation, String jobDescription) {
        this.category = category;
        this.equipmentName = equipmentName;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEquipmentName() {
        return equipmentName;
    }


    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public Job(int jobID) {
        this.jobID = jobID;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobID='" + jobID + '\'' +
                ", category='" + category + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", jobLocation='" + jobLocation + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", accepted=" + accepted +
                '}';
    }
}