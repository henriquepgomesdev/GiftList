package com.giftlist.giftproduct.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.giftlist.giftbuyer.domain.GiftBuyer;
import com.giftlist.giftlist.domain.GiftList;
import com.giftlist.product.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gift_product")
@Data
@NoArgsConstructor
public class GiftProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "link")
    private String link;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JsonManagedReference
    private Product product;

    @OneToMany(mappedBy = "giftProduct")
    @JsonManagedReference
    private List<GiftBuyer> giftBuyers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_gift_list")
    @JsonBackReference
    private GiftList giftList;

}