package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable, Comparable<Searchable> {
    private String productName;
    private final UUID id;

    public Product(UUID id, String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Не указано название продукта!");
        }
        this.productName = productName;
        this.id = id;
    }

    public String getName() {
        return this.productName;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getName();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }

    @Override
    public int compareTo(Searchable o) {
        return 0;
    }

    @Override
    public UUID getId(){
        return this.id;
    }
}
