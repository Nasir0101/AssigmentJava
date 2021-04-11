package net.codejava.DbModels;

import javax.persistence.*;

@Entity
@Table(name = "reclaims")
public class Reclaims {
    @Id
    @Column(name = "reclaim_id")
    private Integer reclaimId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "assigned_employee_id")
    private Integer assignedEmployeeId;

    @Column(name = "reclaim_date")
    private java.sql.Date reclaimDate;

    @Column(name = "reclaim_reason")
    private String reclaimReason;

    public Integer getReclaimId() {
        return this.reclaimId;
    }

    public void setReclaimId(Integer reclaimId) {
        this.reclaimId = reclaimId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAssignedEmployeeId() {
        return this.assignedEmployeeId;
    }

    public void setAssignedEmployeeId(Integer assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public java.sql.Date getReclaimDate() {
        return this.reclaimDate;
    }

    public void setReclaimDate(java.sql.Date reclaimDate) {
        this.reclaimDate = reclaimDate;
    }

    public String getReclaimReason() {
        return this.reclaimReason;
    }

    public void setReclaimReason(String reclaimReason) {
        this.reclaimReason = reclaimReason;
    }
}
