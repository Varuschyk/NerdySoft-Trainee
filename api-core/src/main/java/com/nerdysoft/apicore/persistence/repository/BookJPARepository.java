package com.nerdysoft.apicore.persistence.repository;

import com.nerdysoft.apicore.persistence.entity.BookEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookJPARepository extends JpaRepository<BookEntity, Long> {


  List<BookEntity> findByMembersName(@Nonnull final String memberName);

  List<BookEntity> findAllByMembersIsNotEmptyAndTitle(@Nonnull final String title);

  boolean existsByMembersIsNotEmptyAndId(@Nonnull final Long id);

  BookEntity deleteByAmount(@Nonnull final Long id);

  List<BookEntity> findByTitle(@Nonnull final String title);

  Optional<BookEntity> findByAuthorAndTitle(@Nonnull final String authorName,
                                            @Nonnull final String title);
}
