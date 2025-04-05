package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Component
public class ProductBasket {
    private final Map<UUID, Integer> basketMap;

    public ProductBasket() {
        this.basketMap = new HashMap<>();
    }

    public void put(UUID id){
        basketMap.merge(id, 1, Integer::sum);
    }

    public Map<UUID, Integer> get(){
        return Collections.unmodifiableMap(basketMap);
    }
}
