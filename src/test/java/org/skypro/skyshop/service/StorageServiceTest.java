package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class StorageServiceTest {
    @Mock
    private Map<UUID, Product> mockProductMap;
    @Mock
    private Map<UUID, Article> mockArticleMap;
    //    @InjectMocks
    private StorageService storageService;

    private final UUID testId = UUID.randomUUID();
    private final Product testProduct =
            new SimpleProduct(testId, "Test Product", 100);
    private final Article testArticle =
            new Article(UUID.randomUUID(), "Test Article", "Description");

    @BeforeEach
    void setUp() {
        storageService = new StorageService(mockProductMap, mockArticleMap);
        lenient().when(mockProductMap.values()).thenReturn(List.of(testProduct));
        lenient().when(mockProductMap.get(testId)).thenReturn(testProduct);
        lenient().when(mockArticleMap.values()).thenReturn(List.of(testArticle));
    }

    @Test
    public void givenProduct_whenNotNullResult_ThenAvailable() {
        Collection<Product> result = storageService.getProducts();
        Assertions.assertNotNull(result);
    }

    @Test
    public void givenProduct_whenNotNullResult_ThenAvailableOnlyOne() {
        Collection<Product> result = storageService.getProducts();
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void givenArticle_whenNotNullResult_ThenAvailable() {
        Collection<Article> result = storageService.getArticles();
        Assertions.assertNotNull(result);
    }

    @Test
    public void givenArticle_whenNotNullResult_ThenAvailableOnlyOne() {
        Collection<Article> result = storageService.getArticles();
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void givenProduct_WhenNullResult_ThenFalsePresent() {
        lenient().when(mockProductMap.get(testId)).thenReturn(null);

        Optional<Product> result = storageService.getProductById(testId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void givenProduct_WhenNotNullResult_ThenTruePresent() {
        System.out.println("testId = " + testId);
        Optional<Product> result = storageService.getProductById(testId);
        System.out.println(result);
        Assertions.assertEquals(testProduct, result.get());
    }

}
