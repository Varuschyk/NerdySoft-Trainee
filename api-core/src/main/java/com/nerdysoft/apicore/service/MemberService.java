package com.nerdysoft.apicore.service;

import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberWritePojo;
import jakarta.annotation.Nonnull;

/**
 * Abstraction over MemberEntity services.
 */
public interface MemberService {

  /**
   * Retrieves the member by the given id.
   *
   * @param id the given member id.
   * @return the member by the given id.
   */
  MemberReadPojo get(@Nonnull Long id);

  /**
   * Creates a new member with the given data.
   *
   * @param memberWritePojo the member to create.
   * @return the created member.
   */
  MemberReadPojo create(@Nonnull MemberWritePojo memberWritePojo);

  /**
   * Updates the member by the given id with the given data.
   *
   * @param id the id of the member to update.
   * @param memberWritePojo the member data to update.
   * @return the updated member.
   */
  MemberReadPojo update(@Nonnull Long id,
                        @Nonnull MemberWritePojo memberWritePojo);

  /**
   * Deletes the member by the given static id.
   *
   * @param id the id of the member to delete.
   * @return the deleted member.
   */
  MemberReadPojo delete(@Nonnull Long id);
}
