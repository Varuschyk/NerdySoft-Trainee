package com.nerdysoft.apicore.persistence.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

/**
 * Entity that represents Book.
 */
@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "title", nullable = false)
  @Pattern(regexp = "^[A-Z].*$",
      message = "Title must start from capital letter.")
  @NotBlank String title;

  @Column(name = "author", nullable = false)
  @Pattern(regexp = "^[A-Z][a-z]+\\s[A-Z][a-z]+$",
      message = "Author doesn't match required initials. Example: Olekandr Varush")
  @Length(min = 3)
  @NotBlank String author;

  @Column(name = "amount", nullable = false)
  @NotNull Integer amount;

  @ManyToMany(mappedBy = "borrowedBooks", fetch = FetchType.LAZY)
  @NotNull List<MemberEntity> members;
}
