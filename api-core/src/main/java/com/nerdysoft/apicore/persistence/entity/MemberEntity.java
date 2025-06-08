package com.nerdysoft.apicore.persistence.entity;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "members")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "name", nullable = false)
  @NotBlank String name;

  @CreatedDate
  @Column(name = "membership_date", nullable = false)
  @NotNull Instant membershipDate;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "member_book",
      joinColumns = @JoinColumn(name = "member_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  @NotNull List<BookEntity> borrowedBooks;
}
