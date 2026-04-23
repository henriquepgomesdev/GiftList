package com.giftlist.giftclient.mapper;

import com.giftlist.giftclient.domain.GiftClient;
import com.giftlist.giftclient.model.GiftClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiftClientMapper {
    GiftClientDto toDTO(GiftClient entity);

    GiftClient toEntity(GiftClientDto dto);
}
