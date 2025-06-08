package com.nerdysoft.apicore.controller;

import com.nerdysoft.apicore.mapper.book.BookMapper;
import com.nerdysoft.apicore.mapper.member.MemberMapper;
import com.nerdysoft.apicore.service.LibraryService;
import com.nerdysoft.webapi.controller.library.LibraryActionController;
import com.nerdysoft.webapi.dto.book.BookResponseDto;
import com.nerdysoft.webapi.dto.book.BooksResponseDto;
import com.nerdysoft.webapi.dto.library.BorrowBooksRequestDto;
import com.nerdysoft.webapi.dto.library.ReturnBooksRequestDto;
import com.nerdysoft.webapi.dto.member.MemberResponseDto;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/library")
@RequiredArgsConstructor
public class LibraryControllerImpl implements LibraryActionController {

	private final LibraryService libraryService;
	private final MemberMapper memberMapper;
	private final BookMapper bookMapper;

	@Nonnull
	@Override
	@GetMapping("/book/borrowed/member")
	public List<BookResponseDto> getBorrowedByMemberName(@Valid @NotBlank @RequestParam("memberName") final String memberName) {
		final var bookReadPojo = libraryService.getBorrowedBooksByMember(memberName);
		return bookReadPojo.stream().map(bookMapper::toBookResponseDto).toList();
	}

	@Nonnull
	@Override
	@GetMapping("/book/borrowed/title")
	public BooksResponseDto getBorrowedByTitle(@Valid @NotBlank @RequestParam("title") final String title) {
		final var booksReadPojo = libraryService.getBorrowedBooksByTitle(title);
		final var booksResponseDto = booksReadPojo.stream().map(bookMapper::toBookResponseDto).toList();
		return BooksResponseDto.builder().books(booksResponseDto).borrowedSize(booksResponseDto.size()).build();
	}

	@Nonnull
	@Override
	@PostMapping("/book/borrow")
	public MemberResponseDto borrowBook(@Valid @NotNull @RequestBody final BorrowBooksRequestDto borrowBooksRequestDto) {
		final var memberBorrowedBooks = libraryService.borrowBook(borrowBooksRequestDto.getMemberId(), borrowBooksRequestDto.getBookId());
		return memberMapper.toMemberResponseDto(memberBorrowedBooks);
	}

	@Nonnull
	@Override
	@DeleteMapping("/book/return")
	public MemberResponseDto returnBook(@Valid @NotNull @RequestBody final ReturnBooksRequestDto returnBooksRequestDto) {
		final var memberBorrowedBooks = libraryService.returnBook(returnBooksRequestDto.getMemberId(), returnBooksRequestDto.getBookId());
		return memberMapper.toMemberResponseDto(memberBorrowedBooks);
	}
}
