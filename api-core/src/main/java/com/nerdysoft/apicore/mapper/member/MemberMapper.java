package com.nerdysoft.apicore.mapper.member;

import com.nerdysoft.apicore.persistence.entity.MemberEntity;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberWritePojo;
import com.nerdysoft.webapi.dto.member.MemberRequestDto;
import com.nerdysoft.webapi.dto.member.MemberResponseDto;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MemberMapper {

  MemberReadPojo toMemberReadPojo(@Nonnull final MemberEntity memberEntity);

  MemberEntity toMemberEntity(@Nonnull final MemberWritePojo memberWritePojo);

  MemberResponseDto toMemberResponseDto(@Nonnull final MemberReadPojo memberReadPojo);

  MemberWritePojo toMemberWritePojo(@Nonnull final MemberRequestDto memberRequestDto);
}
