package org.skypro.skyshop.model.product;

import java.util.UUID;

/**
 * Товары с дисконтом
 */
public class DiscountedProduct extends Product {
    int price;
    int discount;

    public DiscountedProduct(UUID id, String productName, int price, int discount) {
        super(id, productName);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена продукта '" + productName +
                    "' должна быть больше 0!");
        } else if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка на продукт '" + productName +
                    "' должна быть в диапазоне от 0% до 100%");
        }
        this.price = price;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return price - price * discount / 100;
    }

    @Override
    public String toString() {
        return getName() + " со скидкой: " + getPrice() + " (" + discount + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}
