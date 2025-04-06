package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBasket {
    private final List<BasketItem> basketItemList;
    private int total;

    public UserBasket(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
        this.total = 0;
        basketItemList
                .forEach(
                        value->total += value.getProduct().getPrice() * value.getCount()
                );
    }

    public List<BasketItem> getBasketItemList() {
        return basketItemList;
    }

    public int getTotal() {
        return total;
    }
}
