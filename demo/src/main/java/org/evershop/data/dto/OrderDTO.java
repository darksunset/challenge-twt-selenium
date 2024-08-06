package org.evershop.data.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private String  orderNumber;
    private String paymentMethod;
    private String contactFullName;
    private String contactEmail;
    private AddressDTO shippingAddress;
    private AddressDTO billingAddress;
    private List<OrderProductDTO> productList = new ArrayList<>();
    private Float deliveryCharge = 0.0f;
    private String shippingMethod;
    private Float discount = 0.0f;
    private Float grandTotal = 0.0f;

    public OrderDTO(){

    }

    public void calculateGrandTotal() {
        calculateSubtotals();
        Float sum = 0.0F;
        for (OrderProductDTO orderProductDTO : this.productList) {
            sum +=orderProductDTO.getSubTotal();
        }
        sum+=this.deliveryCharge;
        sum-=this.discount;
        this.grandTotal = sum;
    }
    private void calculateSubtotals() {
        for (OrderProductDTO orderProductDTO : this.productList) {
            Float subtotal = orderProductDTO.getQuantity() * orderProductDTO.getProduct().getUnitPrice();
            orderProductDTO.setSubTotal(subtotal);
        }
    }
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getContactFullName() {
        return contactFullName;
    }

    public void setContactFullName(String contactFullName) {
        this.contactFullName = contactFullName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public AddressDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public AddressDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressDTO billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<OrderProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderProductDTO> productList) {
        this.productList = productList;
    }

    public Float getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Float deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Float grandTotal) {
        this.grandTotal = grandTotal;
    }
}
