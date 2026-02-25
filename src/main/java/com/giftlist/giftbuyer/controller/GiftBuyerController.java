package com.giftlist.giftbuyer.controller;

import com.giftlist.giftbuyer.domain.GiftBuyer;
import com.giftlist.giftbuyer.service.GiftBuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gift-buyer")
@RequiredArgsConstructor
public class GiftBuyerController {

    private final GiftBuyerService giftBuyerService;

    @GetMapping
    public ResponseEntity<List<GiftBuyer>> getAllProducts() {
        List<GiftBuyer> products = giftBuyerService.findAllGiftBuyers();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftBuyer> getProductById(@PathVariable Long id) {
        return giftBuyerService.findGiftBuyerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GiftBuyer> createProduct(@RequestBody GiftBuyer product) {
        GiftBuyer savedProduct = giftBuyerService.saveGiftBuyer(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiftBuyer> updateProduct(@PathVariable Long id, @RequestBody GiftBuyer product) {
        return giftBuyerService.findGiftBuyerById(id)
                .map(existingProduct -> {
                    GiftBuyer updatedProduct = giftBuyerService.saveGiftBuyer(existingProduct);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<GiftBuyer> productOpt = giftBuyerService.findGiftBuyerById(id);
        if (productOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        giftBuyerService.deleteGiftBuyer(id);
        return ResponseEntity.noContent().build();
    }
}
