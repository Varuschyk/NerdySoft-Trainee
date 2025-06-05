package com.nerdysoft.apicore.persistence.entity;

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

@Entity
@Table
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
  @NotBlank String title;

  @Column(name = "author", nullable = false)
  @NotBlank String author;

  @Column(name = "amount", nullable = false)
  @NotNull Integer amount;

  @ManyToMany(mappedBy = "borrowedBooks", fetch = FetchType.LAZY)
  @NotNull List<MemberEntity> members;
}
