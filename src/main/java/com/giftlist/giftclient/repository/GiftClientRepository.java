package com.giftlist.giftclient.repository;

import com.giftlist.giftclient.domain.GiftClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftClientRepository extends JpaRepository<GiftClient, Long> {

    List<GiftClient> findAllByPeopleCpf(String cpf);

}
