package com.giftlist.giftproduct.mapper;

import com.giftlist.giftlist.domain.GiftList;
import com.giftlist.giftlist.model.GiftListDto;
import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.giftproduct.model.GiftProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiftProductMapper {
    GiftProductDto toDTO(GiftProduct entity);

    GiftProduct toEntity(GiftProductDto dto);
}
