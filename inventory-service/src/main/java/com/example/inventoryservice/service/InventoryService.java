package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

//    @Autowired
//    private InventoryRepository inventoryRepository;
    private final InventoryRepository inventoryRepository;

    @Transactional
    public List<InventoryResponse> isInStock(List <String> skuCode) {

       return inventoryRepository.findBySkuCodeIn(skuCode).stream()
               .map(inventory ->
                   InventoryResponse.builder()
                           .skuCode(inventory.getSkuCode())
                           .isInStock(inventory.getQuantity()>0)
                           .build()
               ).toList();
    }

    public Inventory save(Inventory inventory) {

        return inventoryRepository.save(inventory);
    }
}


