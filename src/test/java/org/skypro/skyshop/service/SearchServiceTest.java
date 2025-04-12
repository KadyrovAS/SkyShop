package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        lenient().when(storageService.getSearchable())
                .thenReturn(List.of(
                        new SimpleProduct(UUID.randomUUID(), "test product name1", 100),
                        new SimpleProduct(UUID.randomUUID(), "test product name2", 200),
                        new SimpleProduct(UUID.randomUUID(), "test product name3", 300)
                ));

    }

    @Test
    public void givenSearchable_whenSomeSimpleProduct_ThenSearchOne() {
        List<SearchResult> results = searchService.search("name1");
        String result = results.stream().findFirst().get().getName();
        Assertions.assertTrue(result.contains("name1"));
    }

    @Test
    public void givenSearchable_whenSomeSimpleProducts_ThenSearchThree() {
        List<SearchResult> results = searchService.search("test product");
        Assertions.assertEquals(3, results.size());
    }

    @Test
    public void givenSearchable_whenSomeSimpleProducts_ThenSearchNull() {
        List<SearchResult> results = searchService.search("name4");
        Assertions.assertTrue(results.isEmpty());
    }

}
