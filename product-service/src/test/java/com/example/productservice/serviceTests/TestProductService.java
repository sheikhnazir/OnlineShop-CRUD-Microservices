package com.example.productservice.serviceTests;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestProductService {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testCreateProduct() {
        ProductRequest productRequest = new ProductRequest("Test Product", "Test Description", 100);
        Product product = Product.builder()
                .name("Test Product")
                .description("Test Description")
                .price(100)
                .build();

        // Mock the productRepository's save method
        when(productRepository.save(product)).thenReturn(product);

        System.out.println(product);
        // Call the createProduct method
        productService.createProduct(productRequest);

        // Verify that the productRepository's save method was called once with the expected product
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testGetAllProducts() {
        // Create some sample Product objects for testing
        Product product1 = new Product(1, "Product 1", "Description 1", 100);
        Product product2 = new Product(2, "Product 2", "Description 2", 200);

        // Mock the productRepository's findAll method
        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        // Call the getAllProducts method
        List<ProductResponse> productResponses = productService.getAllProducts();

        // Verify that the productRepository's findAll method was called once
        verify(productRepository, times(1)).findAll();

        // Assert that the returned list contains the expected ProductResponse objects
        assertEquals(2, productResponses.size());
        assertEquals(1, productResponses.get(0).getId());
        assertEquals("Product 1", productResponses.get(0).getName());
        assertEquals("Description 1", productResponses.get(0).getDescription());
        assertEquals(100, productResponses.get(0).getPrice());

        assertEquals(2, productResponses.get(1).getId());
        assertEquals("Product 2", productResponses.get(1).getName());
        assertEquals("Description 2", productResponses.get(1).getDescription());
        assertEquals(200, productResponses.get(1).getPrice());
    }
}
