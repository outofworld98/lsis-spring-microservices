package com.lsis.order;



public class OrderInfo {
    private Order order;
    private Customer customer;

    public OrderInfo(Order order, Customer customer) {
        this.order = order;
        this.customer = customer;
    }

    public OrderInfo() {
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "order=" + order +
                ", customer=" + customer +
                '}';
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
