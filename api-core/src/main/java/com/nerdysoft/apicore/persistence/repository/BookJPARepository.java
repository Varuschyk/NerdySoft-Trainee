package com.nerdysoft.apicore.persistence.repository;

import com.nerdysoft.apicore.persistence.entity.BookEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookJPARepository extends JpaRepository<BookEntity, Long> {

  Optional<BookEntity> findByTitleAndAuthor(@Nonnull final String title,
                                            @Nonnull final String author);
}
