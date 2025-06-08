package com.nerdysoft.apicore.persistence.repository;

import com.nerdysoft.apicore.persistence.entity.BookEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookJPARepository extends JpaRepository<BookEntity, Long> {

  List<BookEntity> findAllByMembersIsNotEmptyAndTitle(@Nonnull final String title);

  List<BookEntity> findAllByMembersIsNotEmptyAndMembersName(@Nonnull final String memberName);

  @Modifying
  @Query(value = """
        INSERT INTO books (author, title, amount) VALUES (:author, :title, 1)
        ON CONFLICT (author, title) DO UPDATE SET amount = books.amount + 1
        """, nativeQuery = true)
  BookEntity upsertBook(@Nonnull @Param("author") final String author,
                        @Nonnull @Param("title") final String title);

  @Modifying
  @Query(value = """
        UPDATE BookEntity b
        SET b.title = :title, b.author = :author, b.amount = :amount
        WHERE b.id = :id
        """, nativeQuery = true)
  Optional<BookEntity> updateBookById(@Nonnull @Param("id") final Long id,
                                      @Nonnull @Param("title") final String title,
                                      @Nonnull @Param("author") final String author,
                                      @Param("amount") final int amount);
}
