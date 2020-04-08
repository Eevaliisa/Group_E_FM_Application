package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "a0099663_FM_Application", name = "technician")

public class Technician {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "technicianID")
    private String technicianID;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "trade")
    private String trade;

    @Column(name = "mobileNo")
    private String mobileNo;

    @Column(name = "location")
    private String technicianLocation;

    @OneToMany (mappedBy = "technician")
    private List<Job> technicianJobs;


    public Technician() {
    }

    public Technician(String technicianID, String firstName, String lastName, String trade, String mobileNo, String technicianLocation) {
        this.technicianID = technicianID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trade = trade;
        this.mobileNo = mobileNo;
        this.technicianLocation = technicianLocation;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getTechnicianLocation() {
        return technicianLocation;
    }

    public void setTechnicianLocation(String technicianLocation) {
        this.technicianLocation = technicianLocation;
    }

    public List<Job> getTechnicianJobs() {
        return technicianJobs;
    }

    public void setTechnicianJobs(List<Job> technicianJobs) {
        this.technicianJobs = technicianJobs;
    }

    @Override
    public String toString() {
        return "Technician{" +
                "id='" + technicianID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", trade='" + trade + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", technicianLocation='" + technicianLocation + '\'' +
                '}';
    }
}
