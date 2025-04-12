package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    private UUID existingProductId;
    private UUID nonExistingProductId;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        existingProductId = UUID.randomUUID();
        nonExistingProductId = UUID.randomUUID();
        testProduct = new SimpleProduct(existingProductId, "Test Product", 100);
    }

    @Test
    void givenStorageService_whenExistingProduct_thenVerifyGet() {
        when(storageService.getProductById(existingProductId))
                .thenReturn(Optional.of(testProduct));

        basketService.put(existingProductId);

        verify(storageService).getProductById(existingProductId);
    }

    @Test
    void givenStorageService_whenExistingProduct_thenVerifyPut() {
        when(storageService.getProductById(existingProductId))
                .thenReturn(Optional.of(testProduct));

        basketService.put(existingProductId);

        verify(productBasket).put(existingProductId);
    }

    @Test
    void givenStorageService_whenNonExistingProduct_thenVerifyException() {
        when(storageService.getProductById(nonExistingProductId))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> {
            basketService.put(nonExistingProductId);
        });
    }

    @Test
    void givenStorageService_whenEmptyUserBasket_thenGetTotal() {
        UserBasket userBasket = basketService.getUserBasket();
        Assertions.assertEquals(0, userBasket.getTotal());
    }
}