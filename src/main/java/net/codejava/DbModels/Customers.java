package net.codejava.DbModels;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "customer_comment")
    private String customerComment;

    @Column(name = "organisation_id")
    private Integer organisationId;

    @Column(name = "discount_group")
    private Integer discountGroup;

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerComment() {
        return this.customerComment;
    }

    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
    }

    public Integer getOrganisationId() {
        return this.organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public Integer getDiscountGroup() {
        return this.discountGroup;
    }

    public void setDiscountGroup(Integer discountGroup) {
        this.discountGroup = discountGroup;
    }
}
