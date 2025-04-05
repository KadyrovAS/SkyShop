package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class ShopController{
    private StorageService storageService;

    public ShopController(StorageService storageService){
        this.storageService = storageService;
    }


    @GetMapping("/products")
    public Collection<Product> getAllProducts(){
        return storageService.getProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult>getResult(@RequestParam("pattern") String pattern){
        SearchService service = new SearchService(storageService);
        return  service.search(pattern);
    }
}
