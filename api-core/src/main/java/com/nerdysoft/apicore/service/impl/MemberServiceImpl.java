package com.nerdysoft.apicore.service.impl;

import com.nerdysoft.apicore.exception.member.MemberBadRequestException;
import com.nerdysoft.apicore.exception.member.MemberNotFoundException;
import com.nerdysoft.apicore.mapper.member.MemberMapper;
import com.nerdysoft.apicore.persistence.repository.MemberJPARepository;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberWritePojo;
import com.nerdysoft.apicore.service.MemberService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberJPARepository memberJPARepository;
  private final MemberMapper memberMapper;

  @Nonnull
  @Override
  @Transactional(readOnly = true)
  public MemberReadPojo get(@Nonnull final Long id) {
    final var memberEntity =  memberJPARepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException("Member not found!"));
    return memberMapper.toMemberReadPojo(memberEntity);
  }

  @Nonnull
  @Override
  @Transactional
  public MemberReadPojo create(@Nonnull final MemberWritePojo memberWritePojo) {
    final var memberToSave = memberMapper.toMemberEntity(memberWritePojo);
    final var savedMember = memberJPARepository.save(memberToSave);
    return memberMapper.toMemberReadPojo(savedMember);
  }

  @Nonnull
  @Override
  @Transactional
  public MemberReadPojo update(@Nonnull final Long id,
                               @Nonnull final MemberWritePojo memberWritePojo) {
    final var memberEntity = memberJPARepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException("Member not found!"));
    final var memberEntityToSave = memberMapper.toMemberEntity(memberWritePojo);
    memberEntityToSave.setId(memberEntity.getId());
    final var updatedMemberEntity = memberJPARepository.save(memberEntityToSave);
    return memberMapper.toMemberReadPojo(updatedMemberEntity);
  }

  @Override
  @Transactional
  public MemberReadPojo delete(@Nonnull final Long id) {
    final var memberEntity = memberJPARepository.findById(id)
        .orElseThrow(() -> new MemberNotFoundException("Member not found"));

    if (!memberEntity.getBorrowedBooks().isEmpty()) {
      throw new MemberBadRequestException("Member can not be deleted if it has borrowed books.");
    }

    memberJPARepository.deleteById(id);
    return memberMapper.toMemberReadPojo(memberEntity);
  }
}
