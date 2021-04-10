package net.codejava.hibernate.DbModels;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "order_date")
    private java.sql.Date orderDate;

    @Column(name = "requested_shipping_date")
    private java.sql.Date requestedShippingDate;

    @Column(name = "shipping_date")
    private java.sql.Date shippingDate;

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public java.sql.Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(java.sql.Date orderDate) {
        this.orderDate = orderDate;
    }

    public java.sql.Date getRequestedShippingDate() {
        return this.requestedShippingDate;
    }

    public void setRequestedShippingDate(java.sql.Date requestedShippingDate) {
        this.requestedShippingDate = requestedShippingDate;
    }

    public java.sql.Date getShippingDate() {
        return this.shippingDate;
    }

    public void setShippingDate(java.sql.Date shippingDate) {
        this.shippingDate = shippingDate;
    }
}
