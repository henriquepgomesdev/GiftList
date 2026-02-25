package com.giftlist.giftclient.controller;

import com.giftlist.giftclient.domain.GiftClient;
import com.giftlist.giftclient.service.GiftClientService;
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

/**
 * Controller para gerenciamento de produtos.
 */
@RestController
@RequestMapping("/api/gift-client")
@RequiredArgsConstructor
public class GiftClientController {

    private final GiftClientService giftClientService;

    @GetMapping
    public ResponseEntity<List<GiftClient>> getAllProducts() {
        List<GiftClient> products = giftClientService.findAllGiftClients();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftClient> getProductById(@PathVariable Long id) {
        return giftClientService.findGiftClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GiftClient> createProduct(@RequestBody GiftClient product) {
        GiftClient savedProduct = giftClientService.saveGiftClient(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiftClient> updateProduct(@PathVariable Long id, @RequestBody GiftClient product) {
        return giftClientService.findGiftClientById(id)
                .map(existingProduct -> {
//                    EntityUpdater.copyNonNullProperties(product, existingProduct);
                    GiftClient updatedProduct = giftClientService.saveGiftClient(existingProduct);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<GiftClient> productOpt = giftClientService.findGiftClientById(id);
        if (productOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        giftClientService.deleteGiftClient(id);
        return ResponseEntity.noContent().build();
    }
}
