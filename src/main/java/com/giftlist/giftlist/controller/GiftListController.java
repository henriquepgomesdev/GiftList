package com.giftlist.giftlist.controller;

import com.giftlist.giftlist.domain.GiftList;
import com.giftlist.giftlist.service.GiftListService;
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
@RequestMapping("/api/gift-list")
@RequiredArgsConstructor
public class GiftListController {

    private final GiftListService giftListService;


    @GetMapping("/{id}")
    public ResponseEntity<GiftList> getProductById(@PathVariable Long id) {
        return giftListService.findGiftListById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<GiftList>> getAllProducts() {
        List<GiftList> giftLists = giftListService.findAllGiftLists();
        if (giftLists.isEmpty()) {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(giftLists);
    }

    @PostMapping
    public ResponseEntity<GiftList> createProduct(@RequestBody GiftList product) {
        GiftList savedProduct = giftListService.saveGiftList(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiftList> updateProduct(@PathVariable Long id, @RequestBody GiftList product) {
        return giftListService.findGiftListById(id)
                .map(existingProduct -> {
                    GiftList updatedProduct = giftListService.saveGiftList(existingProduct);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<GiftList> productOpt = giftListService.findGiftListById(id);
        if (productOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        giftListService.deleteGiftList(id);
        return ResponseEntity.noContent().build();
    }
}
