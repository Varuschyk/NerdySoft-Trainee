package com.nerdysoft.apicore.service;

import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberWritePojo;
import jakarta.annotation.Nonnull;

public interface MemberService {

  MemberReadPojo get(@Nonnull Long id);

  MemberReadPojo create(@Nonnull MemberWritePojo MemberWritePojo);

  MemberReadPojo update(@Nonnull Long id,
                        @Nonnull MemberWritePojo MemberWritePojo);

  void delete(@Nonnull Long id);
}
