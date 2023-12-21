package com.requests.project.dto;

public interface InterfaceOrderItem {
    Long getId();
    Double getProductPrice();
    Integer getOrderItemQuantity();
    Long getOrderId();
    Long getProductId();
    Double getOrderItemTotal();
}