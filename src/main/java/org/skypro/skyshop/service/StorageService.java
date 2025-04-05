package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService{
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = getProductMap();
        this.articleMap = getArticleMap();
    }

    public Collection<Product> getProducts() {
        return productMap.values();
    }

    public Collection<Article> getArticles() {
        return articleMap.values();
    }

    private Map<UUID, Article> getArticleMap() {
        Map<UUID, Article> articleMap = new HashMap<>();
        UUID id;

        id = UUID.randomUUID();
        articleMap.put(id, new Article(id, "О хлебе", "Хлеб в разном виде знают и любят во всем мире"));
        id = UUID.randomUUID();
        articleMap.put(id, new Article(id, "О яблоках", "Яблоки содержат воздух"));
        id = UUID.randomUUID();
        articleMap.put(id, new Article(id, "О шоколаде", "Шоколад может быть смертельно опасен для животных"));
        id = UUID.randomUUID();
        articleMap.put(id, new Article(id, "Чудесный картофель", "Картофель помог в создании первых фотографий"));
        id = UUID.randomUUID();
        articleMap.put(id, new Article(id, "О морковке", "В моркови больше сахара, чем в клубнике"));
        return articleMap;
    }

    private Map<UUID, Product> getProductMap() {
        Map<UUID, Product> productMap = new HashMap<>();
        UUID id;

        id = UUID.randomUUID();
        productMap.put(id, new FixPriceProduct(id, "Хлеб"));
        id = UUID.randomUUID();
        productMap.put(id, new DiscountedProduct(id, "Масло сливочное", 500, 10));
        id = UUID.randomUUID();
        productMap.put(id, new SimpleProduct(id, "Молоко", 120));
        id = UUID.randomUUID();
        productMap.put(id, new SimpleProduct(id,"Сахар", 50));
        id = UUID.randomUUID();
        productMap.put(id, new SimpleProduct(id, "Соль", 30));
        return productMap;
    }

    public List<Searchable> getSearchable(){
        List<Searchable>listSearchable = Stream.concat(
                articleMap.values().stream(),
                productMap.values().stream()
        ).collect(Collectors.toList());
        return listSearchable;
    }
}
