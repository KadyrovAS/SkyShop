package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void put(UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if (product.isEmpty()) {
            throw new NoSuchProductException("123456", "Ошибка! Такого товара нет!");
        }
        productBasket.put(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItemList = new LinkedList<>();

        productBasket.get()
                .forEach((id, value) -> basketItemList.add(
                        new BasketItem(
                                storageService.getProductById(id).get(), value
                        )
                ));

        return new UserBasket(basketItemList);
    }
}
