package com.nerdysoft.apicore.persistence.repository;

import java.util.Optional;
import com.nerdysoft.apicore.persistence.entity.BookEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of {@link BookEntity}.
 */
@Repository
public interface BookJPARepository extends JpaRepository<BookEntity, Long> {

  Optional<BookEntity> findByTitleAndAuthor(@Nonnull final String title,
                                            @Nonnull final String author);
}
