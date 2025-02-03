package model;

import strategy.PricingStrategy;

import java.sql.Blob;

/*
* public class ProductData
{
    private String productId;
    private String productCategory;
    private String productName;
    private String productStatus;
    private Blob productImage;
    private Double productPrice;

    public ProductData(String productId, String productCategory, String productName, String productStatus, Blob productImage, Double productPrice)
    {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productStatus = productStatus;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }

    public String getProductId() { return productId; }
    public String getProductCategory() { return productCategory; }
    public String getProductName() { return productName; }
    public String getProductStatus() { return productStatus; }
    public Blob getProductImage() { return productImage; }
    public Double getProductPrice() { return productPrice; }
}
*/

public class ProductData {
    private String productId;
    private String productCategory;
    private String productName;
    private String productStatus;
    private Blob productImage;
    private Double basePrice;
    private PricingStrategy pricingStrategy;

    public ProductData(String productId, String productCategory, String productName, String productStatus, Blob productImage, Double basePrice, PricingStrategy pricingStrategy) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productStatus = productStatus;
        this.productImage = productImage;
        this.basePrice = basePrice;
        this.pricingStrategy = pricingStrategy;
    }

    public String getProductId() { return productId; }
    public String getProductCategory() { return productCategory; }
    public String getProductName() { return productName; }
    public String getProductStatus() { return productStatus; }
    public double getPrice() { return pricingStrategy.calculatePrice(basePrice); }

    @Override
    public String toString() {
        return "ProductData{" +
                "productId='" + productId + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productName='" + productName + '\'' +
                ", productStatus='" + productStatus + '\'' +
                ", productPrice=" + getPrice() +
                '}';
    }

    public String getName() {
        return  productName;
    }
}
