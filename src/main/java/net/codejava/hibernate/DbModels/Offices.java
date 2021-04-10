package net.codejava.hibernate.DbModels;

import javax.persistence.*;

@Entity
@Table(name = "offices")
public class Offices {
    @Id
    @Column(name = "office_id")
    private Integer officeId;

    @Column(name = "office_name")
    private String officeName;

    @Column(name = "address")
    private String address;

    public Integer getOfficeId() {
        return this.officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return this.officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
