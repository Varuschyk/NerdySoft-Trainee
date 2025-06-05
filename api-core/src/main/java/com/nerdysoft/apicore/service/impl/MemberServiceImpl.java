package com.nerdysoft.apicore.service.impl;

import com.nerdysoft.apicore.mapper.member.MemberMapper;
import com.nerdysoft.apicore.persistence.repository.MemberJPARepository;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberWritePojo;
import com.nerdysoft.apicore.service.MemberService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberJPARepository memberJPARepository;
  private final MemberMapper memberMapper;

  @Nonnull
  @Override
  public MemberReadPojo get(@Nonnull final Long id) {
    final var memberEntity =  memberJPARepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Member not found!"));
    return memberMapper.toMemberReadPojo(memberEntity);
  }

  @Nonnull
  @Override
  public MemberReadPojo create(@Nonnull final MemberWritePojo memberWritePojo) {
    final var memberToSave = memberMapper.toMemberEntity(memberWritePojo);
    final var savedMember = memberJPARepository.save(memberToSave);
    return memberMapper.toMemberReadPojo(savedMember);
  }

  @Nonnull
  @Override
  public MemberReadPojo update(@Nonnull final Long id,
                               @Nonnull final MemberWritePojo memberWritePojo) {
    final var memberEntity = memberJPARepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Member not found"));
    memberEntity.setName(memberWritePojo.getName());
    final var updateMemberEntity = memberJPARepository.save(memberEntity);
    return memberMapper.toMemberReadPojo(updateMemberEntity);
  }

  @Nonnull
  @Override
  public void delete(@Nonnull final Long id) {
    memberJPARepository.findById(id)
        .ifPresentOrElse(memberJPARepository::delete, () -> new RuntimeException("Member not found"));
  }
}
