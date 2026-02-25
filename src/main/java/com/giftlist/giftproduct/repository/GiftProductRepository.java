package com.giftlist.giftproduct.repository;


import com.giftlist.giftproduct.domain.GiftProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftProductRepository extends JpaRepository<GiftProduct, Long> {

}
