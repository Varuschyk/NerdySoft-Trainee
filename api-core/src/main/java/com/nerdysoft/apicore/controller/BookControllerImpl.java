package com.nerdysoft.apicore.controller;

import java.util.List;
import com.nerdysoft.apicore.mapper.book.BookMapper;
import com.nerdysoft.apicore.service.BookService;
import com.nerdysoft.webapi.controller.book.BookController;
import com.nerdysoft.webapi.dto.book.BookRequestDto;
import com.nerdysoft.webapi.dto.book.BookResponseDto;
import com.nerdysoft.webapi.dto.book.BooksResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

  private final BookService bookService;
  private final BookMapper bookMapper;

  @Override
  @GetMapping("/book/{id}")
  public BookResponseDto get(@Valid @NotNull @PathVariable("id") final Long id) {
    final var bookReadPojo = bookService.get(id);
    return bookMapper.toBookResponseDto(bookReadPojo);
  }

  @GetMapping("/book/borrowed")
  public List<BookResponseDto> getBorrowedByMemberName(@Valid @NotBlank @RequestParam("memberName") final String memberName) {
    final var bookReadPojo = bookService.get(memberName);
    return bookReadPojo.stream().map(bookMapper::toBookResponseDto).toList();
  }

  @GetMapping("/book/title")
  public BooksResponseDto getBorrowedByTitle(@Valid @NotBlank @RequestParam("title") final String title) {
    final var booksReadPojo = bookService.getBorrowedByTitle(title);
    final var booksResponseDto = booksReadPojo.stream().map(bookMapper::toBookResponseDto).toList();
    return BooksResponseDto.builder().books(booksResponseDto).size(booksResponseDto.size()).build();
  }

  @Override
  @PostMapping("/book")
  public BookResponseDto create(@Valid @NotNull @RequestBody final BookRequestDto bookRequestDto) {
    final var bookWritePojo = bookMapper.toBookWritePojo(bookRequestDto);
    final var bookReadPojo = bookService.create(bookWritePojo);
    return bookMapper.toBookResponseDto(bookReadPojo);
  }

  @Override
  @PutMapping("/book/{id}")
  public BookResponseDto update(@Valid @NotNull @PathVariable("id") final Long id,
                                @Valid @NotNull @RequestBody  final BookRequestDto bookRequestDto) {
    final var bookWritePojo = bookMapper.toBookWritePojo(bookRequestDto);
    final var bookReadPojo = bookService.update(id, bookWritePojo);
    return bookMapper.toBookResponseDto(bookReadPojo);
  }

  @Override
  @DeleteMapping("/book/{id}")
  public BookResponseDto delete(@Valid @NotNull @PathVariable("id") final Long id) {
    final var bookReadPojo = bookService.delete(id);
    return bookMapper.toBookResponseDto(bookReadPojo);
  }
}
