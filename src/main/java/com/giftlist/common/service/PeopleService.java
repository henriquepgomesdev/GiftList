package com.giftlist.common.service;


import com.giftlist.common.domain.People;

import java.util.Optional;

public interface PeopleService {

    Optional<People> findPeopleByCpf(String cpf);

}