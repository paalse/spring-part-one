package ru.paalse.persist;

import ru.paalse.service.ProductRepr;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String productname;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    public Product() {
    }

    public Product(String productname) {
        this.productname = productname;
    }

    public Product(ProductRepr product) {
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