package com.giftlist.common.service;

import com.giftlist.common.domain.People;
import com.giftlist.common.repository.PeopleRepository;
import com.giftlist.product.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    @Override
    public Optional<People> findPeopleByCpf(String cpf) {
        return peopleRepository.findByCpf(cpf);
    }

}