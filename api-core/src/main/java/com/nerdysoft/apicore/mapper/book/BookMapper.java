package com.nerdysoft.apicore.mapper.book;

import com.nerdysoft.apicore.persistence.entity.BookEntity;
import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import com.nerdysoft.webapi.dto.BookResponseDto;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

  BookResponseDto toBookResponseDto(@Nonnull final BookReadPojo bookReadPojo);

  BookReadPojo toBookReadPojo(@Nonnull final BookEntity bookEntity);

  BookEntity toBookEntity(@Nonnull final BookWritePojo bookWritePojo);
}
