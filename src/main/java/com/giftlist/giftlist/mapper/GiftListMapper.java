package com.giftlist.giftlist.mapper;

import com.giftlist.giftclient.domain.GiftClient;
import com.giftlist.giftclient.model.GiftClientDto;
import com.giftlist.giftlist.domain.GiftList;
import com.giftlist.giftlist.model.GiftListDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiftListMapper {
    GiftListDto toDTO(GiftList entity);

    GiftList toEntity(GiftListDto dto);
}
