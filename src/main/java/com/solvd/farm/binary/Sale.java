package com.solvd.farm.binary;
import java.util.Objects;

public class Sale extends BaseEntity {
    private int productId;
    private int customerId;
    private String saleDate;
    private int saleQuantity;

    public Sale(long id, int productId, int customerId, String saleDate, int saleQuantity) {
        super(id);
        this.productId = productId;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.saleQuantity = saleQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return super.getId() == super.getId() &&
                productId == sale.productId &&
                customerId == sale.customerId &&
                saleQuantity == sale.saleQuantity &&
                Objects.equals(saleDate, sale.saleDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), productId, customerId, saleDate, saleQuantity);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + super.getId() +
                ", productId=" + productId +
                ", customerId=" + customerId +
                ", saleDate='" + saleDate + '\'' +
                ", saleQuantity=" + saleQuantity +
                '}';
    }
}