package com.giftlist.giftbuyer.domain;

import com.giftlist.common.domain.People;
import com.giftlist.giftproduct.domain.GiftProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gift_buyer")
@Data
@NoArgsConstructor
public class GiftBuyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gift_product_id")
    private GiftProduct giftProduct;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private People people;
}