package com.giftlist.giftbuyer.controller;

import com.giftlist.giftbuyer.domain.GiftBuyer;
import com.giftlist.giftbuyer.model.GiftBuyerDto;
import com.giftlist.giftbuyer.model.GiftBuyerInput;
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
    public ResponseEntity<List<GiftBuyerDto>> getAllProducts() {
        return ResponseEntity.ok(giftBuyerService.findAllGiftBuyers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftBuyerDto> getProductById(@PathVariable Long id) {
        return giftBuyerService.findGiftBuyerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GiftBuyerDto> createProduct(@RequestBody GiftBuyerInput input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(giftBuyerService.saveGiftBuyer(input, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<GiftBuyerDto> productOpt = giftBuyerService.findGiftBuyerById(id);
        if (productOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        giftBuyerService.deleteGiftBuyer(id);
        return ResponseEntity.noContent().build();
    }
}
