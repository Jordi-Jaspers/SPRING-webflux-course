package com.jordijaspers.webfluxapiannotations.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * Product model for MongoDB.
 * <p>
 * Note: Because MongoDB is a NoSQL database, it does not work like relational database.
 * Meaning, the model does not represent a table but a JSON document.
 */
@Document
public class Product {

    /**
     * The product ID.
     */
    @Id
    private String id;

    /**
     * The product name.
     */
    private String name;

    /**
     * The product price.
     */
    private Double price;

    /**
     * The default constructor.
     *
     * @param id    The product ID.
     * @param name  The product name.
     * @param price The product price.
     */
    public Product(final String id, final String name, final Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Empty constructor.
     */
    public Product() {
        //EMPTY
    }

    /**
     * Getter for id.
     *
     * @return id .
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id.
     *
     * @param id .
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Getter for name.
     *
     * @return name .
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     *
     * @param name .
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter for price.
     *
     * @return price .
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Setter for price.
     *
     * @param price .
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price,
                product.price);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
