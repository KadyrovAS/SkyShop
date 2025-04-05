package org.skypro.skyshop.model.product;

import java.util.UUID;

/**
 * Товары с фиксированной ценой
 */
public class FixPriceProduct extends Product {
    private final int fixPrice = 100;

    public FixPriceProduct(UUID id, String productName) {
        super(id, productName);
    }

    @Override
    public int getPrice() {
        return this.fixPrice;
    }

    @Override
    public String toString() {
        return getName() + " с фиксированной ценой: Фиксированная цена " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}
