package test.service.book;

import java.util.List;
import java.util.Optional;
import com.nerdysoft.apicore.exception.book.BookBadRequestException;
import com.nerdysoft.apicore.exception.book.BookNotFoundException;
import com.nerdysoft.apicore.mapper.book.BookMapper;
import com.nerdysoft.apicore.persistence.entity.BookEntity;
import com.nerdysoft.apicore.persistence.entity.MemberEntity;
import com.nerdysoft.apicore.persistence.repository.BookJPARepository;
import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.book.BookWritePojo;
import com.nerdysoft.apicore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBookServiceImpl {

	private static final Long BOOK_ID = 1L;
	private static final String BOOK_TITLE = "Java Philosophy";
	private static final String BOOK_AUTHOR = "Bruce Eckel";
	private static final Integer BOOK_AMOUNT = 10;
	private static final BookEntity BOOK_ENTITY = BookEntity.builder()
			.id(BOOK_ID).title(BOOK_TITLE).author(BOOK_AUTHOR).amount(BOOK_AMOUNT).build();

	private static final String MEMBER_NAME = "Alexander";
	private static final List<BookEntity> BORROWED_BOOKS = List.of(BOOK_ENTITY);
	private static final MemberEntity MEMBER_ENTITY = MemberEntity.builder()
			.name(MEMBER_NAME).borrowedBooks(BORROWED_BOOKS).build();

	private static final BookReadPojo BOOK_READ_POJO = BookReadPojo.builder()
			.title(BOOK_TITLE)
			.author(BOOK_AUTHOR)
			.amount(BOOK_AMOUNT)
			.build();
	private static final BookWritePojo BOOK_WRITE_POJO = BookWritePojo.builder()
			.title(BOOK_TITLE)
			.author(BOOK_AUTHOR)
			.amount(BOOK_AMOUNT)
			.build();

	@Mock
	private BookMapper bookMapper;
	@Mock
	private BookJPARepository bookJPARepository;
	@InjectMocks
	private BookServiceImpl bookService;

	@Test
	public void should_GetBookById_Successfully() {
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.of(BOOK_ENTITY));
		when(bookMapper.toBookReadPojo(BOOK_ENTITY))
				.thenReturn(BOOK_READ_POJO);

		final var result = bookService.get(BOOK_ID);
		assertNotNull(result);
		assertEquals(BOOK_READ_POJO, result);

		verify(bookJPARepository, only()).findById(BOOK_ID);
		verify(bookMapper, only()).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_GetBookById_and_ThrowBookNotFoundException() {
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.empty());

		assertThrows(BookNotFoundException.class, () -> bookService.get(BOOK_ID));

		verify(bookJPARepository, only()).findById(BOOK_ID);
	}

	@Test
	public void should_GetBorrowedBook_ByMemberName_Successfully() {
		when(bookJPARepository.findAllByMembersIsNotEmptyAndMembersName(MEMBER_NAME))
				.thenReturn(BORROWED_BOOKS);
		when(bookMapper.toBookReadPojo(BOOK_ENTITY))
				.thenReturn(BOOK_READ_POJO);

		final var result = bookService.getBorrowedByMember(MEMBER_NAME);
		assertNotNull(result);
		assertEquals(List.of(BOOK_READ_POJO), result);

		verify(bookJPARepository, only()).findAllByMembersIsNotEmptyAndMembersName(MEMBER_NAME);
		verify(bookMapper, atLeast(1)).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_GetBorrowedBook_ByMemberName_and_ReturnEmptyList() {
		when(bookJPARepository.findAllByMembersIsNotEmptyAndMembersName(MEMBER_NAME))
				.thenReturn(List.of());

		final var result = bookService.getBorrowedByMember(MEMBER_NAME);
		assertNotNull(result);
		assertTrue(result.isEmpty());

		verify(bookJPARepository, only()).findAllByMembersIsNotEmptyAndMembersName(MEMBER_NAME);
		verify(bookMapper, Mockito.never()).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_GetBorrowedBook_ByTitle_Successfully() {
		when(bookJPARepository.findAllByMembersIsNotEmptyAndTitle(BOOK_TITLE))
				.thenReturn(BORROWED_BOOKS);
		when(bookMapper.toBookReadPojo(BOOK_ENTITY))
				.thenReturn(BOOK_READ_POJO);

		final var result = bookService.getBorrowedByTitle(BOOK_TITLE);
		assertNotNull(result);
		assertEquals(List.of(BOOK_READ_POJO), result);

		verify(bookJPARepository, only()).findAllByMembersIsNotEmptyAndTitle(BOOK_TITLE);
		verify(bookMapper, atLeast(1)).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_GetBorrowedBook_ByTitle_and_ReturnEmptyList() {
		when(bookJPARepository.findAllByMembersIsNotEmptyAndTitle(BOOK_TITLE))
				.thenReturn(List.of());

		final var result = bookService.getBorrowedByTitle(BOOK_TITLE);
		assertNotNull(result);
		assertTrue(result.isEmpty());

		verify(bookJPARepository, only()).findAllByMembersIsNotEmptyAndTitle(BOOK_TITLE);
		verify(bookMapper, never()).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_CreateBook_Successfully() {
		when(bookMapper.toBookEntity(BOOK_WRITE_POJO))
				.thenReturn(BOOK_ENTITY);
		when(bookJPARepository.upsertBook(BOOK_AUTHOR, BOOK_TITLE))
				.thenReturn(BOOK_ENTITY);
		when(bookMapper.toBookReadPojo(BOOK_ENTITY))
				.thenReturn(BOOK_READ_POJO);

		final var result = bookService.create(BOOK_WRITE_POJO);
		assertNotNull(result);
		assertEquals(BOOK_READ_POJO, result);

		verify(bookMapper, times(1)).toBookEntity(BOOK_WRITE_POJO);
		verify(bookJPARepository, only()).upsertBook(BOOK_AUTHOR, BOOK_TITLE);
		verify(bookMapper,  times(1)).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_UpdateBook_Successfully() {
		when(bookJPARepository.updateBookById(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_AMOUNT))
				.thenReturn(Optional.of(BOOK_ENTITY));
		when(bookMapper.toBookReadPojo(BOOK_ENTITY))
				.thenReturn(BOOK_READ_POJO);

		final var result = bookService.update(BOOK_ID, BOOK_WRITE_POJO);
		assertNotNull(result);
		assertEquals(BOOK_READ_POJO, result);

		verify(bookJPARepository, only()).updateBookById(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_AMOUNT);
		verify(bookMapper, only()).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_UpdateBook_and_ThrowBookNotFoundException() {
		when(bookJPARepository.updateBookById(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_AMOUNT))
				.thenReturn(Optional.empty());

		assertThrows(BookNotFoundException.class, () -> bookService.update(BOOK_ID, BOOK_WRITE_POJO));

		verify(bookJPARepository, only()).updateBookById(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_AMOUNT);
	}

	@Test
	public void should_DeleteBook_Successfully() {
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.of(BOOK_ENTITY));
		doNothing().when(bookJPARepository).delete(BOOK_ENTITY);
		when(bookMapper.toBookReadPojo(BOOK_ENTITY))
				.thenReturn(BOOK_READ_POJO);

		final var result = bookService.delete(BOOK_ID);
		assertNotNull(result);
		assertEquals(BOOK_READ_POJO, result);

		verify(bookJPARepository, times(1)).findById(BOOK_ID);
		verify(bookJPARepository, times(1)).delete(BOOK_ENTITY);
		verify(bookMapper, only()).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_DeleteBook_and_ThrowBookNotFoundException() {
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.empty());

		assertThrows(BookNotFoundException.class, () -> bookService.delete(BOOK_ID));

		verify(bookJPARepository, only()).findById(BOOK_ID);
	}

	@Test
	public void should_DeleteBook_and_ThrowBookBadRequestException() {
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.of(BookEntity.builder().members(List.of(MEMBER_ENTITY)).build()));

		assertThrows(BookBadRequestException.class, () -> bookService.delete(BOOK_ID));

		verify(bookJPARepository, only()).findById(BOOK_ID);
	}
}

