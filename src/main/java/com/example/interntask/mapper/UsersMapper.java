package com.example.interntask.mapper;

import com.example.interntask.model.dto.UsersDto;
import com.example.interntask.model.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsersMapper {
    @Mapping(ignore = true, target = "cards.userId")
    UsersDto mapToDto(UsersEntity usersEntity);

    UsersEntity mapToEntity(UsersDto usersDto);

    @Mapping(ignore = true, target = "id")
    UsersEntity mapToEntity(@MappingTarget UsersEntity usersEntity, UsersDto usersDto);
}