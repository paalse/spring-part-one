package ru.paalse.service;

import ru.paalse.persist.Product;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRepr {

    private long id;

    @NotEmpty
    private String productname;

    private String description;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("999999999.99")
    private BigDecimal price;

    public ProductRepr() {
    }

    public ProductRepr(String productname) {
        this.productname = productname;
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.productname = product.getProductname();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
