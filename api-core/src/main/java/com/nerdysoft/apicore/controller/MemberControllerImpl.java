package com.nerdysoft.apicore.controller;

import com.nerdysoft.apicore.mapper.member.MemberMapper;
import com.nerdysoft.apicore.service.MemberService;
import com.nerdysoft.webapi.controller.member.MemberController;
import com.nerdysoft.webapi.dto.member.MemberRequestDto;
import com.nerdysoft.webapi.dto.member.MemberResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController {

  private final MemberService memberService;
  private final MemberMapper memberMapper;

  @Override
  @GetMapping("/member/{id}")
  public MemberResponseDto get(@Valid @NotNull @PathVariable("id") final Long id) {
    final var memberReadPojo = memberService.get(id);
    return memberMapper.toMemberResponseDto(memberReadPojo);
  }

  @Override
  @PostMapping("/member")
  public MemberResponseDto create(@Valid @NotNull @RequestBody final MemberRequestDto memberRequestDto) {
    final var memberWritePojo = memberMapper.toMemberWritePojo(memberRequestDto);
    final var memberReadPojo = memberService.create(memberWritePojo);
    return memberMapper.toMemberResponseDto(memberReadPojo);
  }

  @Override
  @PutMapping("/member/{id}")
  public MemberResponseDto update(@Valid @NotNull @PathVariable("id") final Long id,
                                  @Valid @NotNull @RequestBody final MemberRequestDto memberRequestDto) {
    final var memberWritePojo = memberMapper.toMemberWritePojo(memberRequestDto);
    final var memberReadPojo = memberService.update(id, memberWritePojo);
    return memberMapper.toMemberResponseDto(memberReadPojo);
  }

  @Override
  @DeleteMapping("/member/{id}")
  public MemberResponseDto delete(@Valid @NotNull @PathVariable("id") final Long id) {
    final var deletedMemberReadPojo = memberService.delete(id);
    return memberMapper.toMemberResponseDto(deletedMemberReadPojo);
  }
}
