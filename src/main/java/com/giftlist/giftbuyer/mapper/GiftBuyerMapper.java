package com.giftlist.giftbuyer.mapper;

import com.giftlist.giftbuyer.domain.GiftBuyer;
import com.giftlist.giftbuyer.model.GiftBuyerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiftBuyerMapper {
    GiftBuyerDto toDTO(GiftBuyer entity);

    GiftBuyer toEntity(GiftBuyerDto dto);
}
