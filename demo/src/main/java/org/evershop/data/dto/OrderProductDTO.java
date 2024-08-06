package org.evershop.data.dto;

public class OrderProductDTO {
    private Integer quantity;
    private Float subTotal;
    private ProductDTO product;
    public OrderProductDTO(){}
    public static OrderProductDTO createOrderProduct(Integer quantity, ProductDTO product){
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setQuantity(quantity);
        orderProductDTO.setProduct(product);
        return orderProductDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
