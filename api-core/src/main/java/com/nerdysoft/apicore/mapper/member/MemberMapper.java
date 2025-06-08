package com.nerdysoft.apicore.mapper.member;

import com.nerdysoft.apicore.persistence.entity.MemberEntity;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberWritePojo;
import com.nerdysoft.webapi.dto.book.BookInformationResponseDto;
import com.nerdysoft.webapi.dto.member.MemberBorrowedBooksResponseDto;
import com.nerdysoft.webapi.dto.member.MemberRequestDto;
import com.nerdysoft.webapi.dto.member.MemberResponseDto;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.time.Instant;
import java.util.Collections;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MemberMapper {

  default MemberReadPojo toMemberReadPojo(@Nonnull final MemberEntity memberEntity) {
    return MemberReadPojo.builder()
        .name(memberEntity.getName())
        .membershipDate(memberEntity.getMembershipDate())
        .borrowedBooks(toMemberBorrowedBooksResponseDto(memberEntity))
        .build();
  }

  default MemberEntity toMemberEntity(@Nonnull final MemberWritePojo memberWritePojo) {
    return MemberEntity.builder()
        .name(memberWritePojo.getName())
        .membershipDate(Instant.now())
        .borrowedBooks(Collections.emptyList())
        .build();
  }

  MemberResponseDto toMemberResponseDto(@Nonnull final MemberReadPojo memberReadPojo);

  MemberWritePojo toMemberWritePojo(@Nonnull final MemberRequestDto memberRequestDto);

  default MemberBorrowedBooksResponseDto toMemberBorrowedBooksResponseDto(@Nonnull final MemberEntity memberEntity) {
    return MemberBorrowedBooksResponseDto.builder()
        .size(memberEntity.getBorrowedBooks().size())
        .borrowedBooks(memberEntity.getBorrowedBooks().stream().map(book ->
            BookInformationResponseDto.builder().title(book.getTitle()).author(book.getAuthor()).build()).toList())
        .build();
  }
}
