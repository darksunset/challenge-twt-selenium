package org.evershop.data.dto;

public class ProductDTO {
    private String productName;
    private String color;
    private String size;
    private Float unitPrice;
    private String category;

    public ProductDTO() {
    }

    public static ProductDTO createProductDTO() {
        return new ProductDTO();
    }

    public static ProductDTO createProductDTO(String productName, String color, String size, String category){
        ProductDTO productDTO = new ProductDTO();
        productDTO.productName = productName;
        productDTO.color = color;
        productDTO.size = size;
        productDTO.category = category;
        return  productDTO;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
