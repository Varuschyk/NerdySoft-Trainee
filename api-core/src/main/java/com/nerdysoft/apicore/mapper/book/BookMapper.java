package com.nerdysoft.apicore.mapper.book;

import com.nerdysoft.apicore.persistence.entity.BookEntity;
import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import com.nerdysoft.webapi.dto.book.BookRequestDto;
import com.nerdysoft.webapi.dto.book.BookResponseDto;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Collections;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

  BookResponseDto toBookResponseDto(@Nonnull final BookReadPojo bookReadPojo);

  BookReadPojo toBookReadPojo(@Nonnull final BookEntity bookEntity);

  default BookEntity toBookEntity(@Nonnull final BookWritePojo bookWritePojo) {
    return BookEntity.builder()
        .title(bookWritePojo.getTitle())
        .author(bookWritePojo.getAuthor())
        .amount(bookWritePojo.getAmount())
        .members(Collections.emptyList())
        .build();
  }

  BookWritePojo toBookWritePojo(@Nonnull final BookRequestDto bookRequestDto);
}
