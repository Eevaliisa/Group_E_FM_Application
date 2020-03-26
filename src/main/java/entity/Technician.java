package entity;

import javax.persistence.*;

@Entity
@Table(schema = "FmDB", name = "technician")

public class Technician {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

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

    public Technician() {
    }

    public Technician(String id, String firstName, String lastName, String trade, String mobileNo, String technicianLocation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.trade = trade;
        this.mobileNo = mobileNo;
        this.technicianLocation = technicianLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Technician{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", trade='" + trade + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", technicianLocation='" + technicianLocation + '\'' +
                '}';
    }
}
