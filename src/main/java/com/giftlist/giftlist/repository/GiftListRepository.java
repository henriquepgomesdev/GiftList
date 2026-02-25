package com.giftlist.giftlist.repository;

import com.giftlist.giftlist.domain.GiftList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftListRepository extends JpaRepository<GiftList, Long> {

}
