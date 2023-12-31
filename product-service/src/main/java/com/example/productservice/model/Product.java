    package com.example.productservice.model;;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import lombok.*;
    import java.math.BigDecimal;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    @Entity(name = "products")
    public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private String description;
        private Integer price;
    }
