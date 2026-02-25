package com.giftlist.giftbuyer.repository;


import com.giftlist.giftbuyer.domain.GiftBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftBuyerRepository extends JpaRepository<GiftBuyer, Long> {
}
