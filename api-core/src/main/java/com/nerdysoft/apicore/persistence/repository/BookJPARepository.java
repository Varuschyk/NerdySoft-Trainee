package com.nerdysoft.apicore.persistence.repository;

import com.nerdysoft.apicore.persistence.entity.BookEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookJPARepository extends JpaRepository<BookEntity, Long> {

  @Nonnull
  List<BookEntity> findByMembersName(@Nonnull final String memberName);

  @Nonnull
  List<BookEntity> findByMembersIsNotEmpty(@Nonnull final String title);
}
