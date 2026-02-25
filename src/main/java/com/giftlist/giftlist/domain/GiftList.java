package com.giftlist.giftlist.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.giftlist.giftproduct.domain.GiftProduct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gift_list")
@Data
@NoArgsConstructor
public class GiftList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gift_list")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "giftList")
    @JsonManagedReference
    private List<GiftProduct> products = new ArrayList<>();

    public void addProduct(GiftProduct product) {
        this.products.add(product);
    }
}