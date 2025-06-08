package test.service.library;

import com.nerdysoft.apicore.exception.library.LibraryBadRequestException;
import com.nerdysoft.apicore.mapper.book.BookMapper;
import com.nerdysoft.apicore.mapper.member.MemberMapper;
import com.nerdysoft.apicore.persistence.entity.BookEntity;
import com.nerdysoft.apicore.persistence.entity.MemberEntity;
import com.nerdysoft.apicore.persistence.repository.BookJPARepository;
import com.nerdysoft.apicore.persistence.repository.MemberJPARepository;
import com.nerdysoft.apicore.pojo.book.BookReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.properties.LibraryBookPropertiesHolder;
import com.nerdysoft.apicore.service.impl.LibraryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestLibraryServiceImpl {

	private static final Long BOOK_ID = 1L;
	private static final String BOOK_TITLE = "Java Philosophy";
	private static final String BOOK_AUTHOR = "Bruce Eckel";
	private static final Integer BOOK_AMOUNT = 10;
	private static final BookEntity BOOK_ENTITY = BookEntity.builder()
			.id(BOOK_ID).title(BOOK_TITLE).author(BOOK_AUTHOR).amount(BOOK_AMOUNT).build();
	private static final BookEntity BOOK_ENTITY_WITH_ZERO_AMOUNT = BookEntity.builder()
			.id(BOOK_ID).title(BOOK_TITLE).author(BOOK_AUTHOR).amount(0).build();
	private static final int BORROW_LIMIT = 3;

	private static final String MEMBER_NAME = "Alexander";
	private static final List<BookEntity> BORROWED_BOOKS = List.of(BOOK_ENTITY);
	private static final MemberEntity MEMBER_ENTITY = MemberEntity.builder()
			.name(MEMBER_NAME).borrowedBooks(BORROWED_BOOKS).build();
	private static final MemberEntity MEMBER_ENTITY_WITH_LIMIT_BORROWED_BOOKS = MemberEntity.builder()
			.name(MEMBER_NAME).borrowedBooks(List.of(BOOK_ENTITY, BOOK_ENTITY,BOOK_ENTITY, BOOK_ENTITY,BOOK_ENTITY, BOOK_ENTITY)).build();
	private static final MemberEntity MEMBER_ENTITY_WITH_WRONG_BOOK = MemberEntity.builder()
			.name(MEMBER_NAME).borrowedBooks(List.of(BookEntity.builder().id(99999L).build())).build();
	private static final List<MemberEntity> MEMBERS = List.of(MEMBER_ENTITY);
	private static final MemberEntity MEMBER_WITHOUT_BOOKS = MemberEntity.builder().name(MEMBER_NAME)
			.borrowedBooks(List.of()).build();
	private static final MemberEntity MEMBER_ENTITY_TO_BORROW_BOOK = MemberEntity.builder().name(MEMBER_NAME)
			.borrowedBooks(List.of()).build();

	private static final Long MEMBER_ID = 1L;
	private static final Instant MEMBERSHIP_DATE = Instant.now();

	private static final MemberReadPojo MEMBER_READ_POJO = MemberReadPojo.builder()
			.name(MEMBER_NAME).membershipDate(MEMBERSHIP_DATE).build();

	private static final BookReadPojo BOOK_READ_POJO = BookReadPojo.builder()
			.title(BOOK_TITLE)
			.author(BOOK_AUTHOR)
			.amount(BOOK_AMOUNT)
			.build();

	@Mock private MemberMapper memberMapper;
	@Mock private BookMapper bookMapper;
	@Mock private MemberJPARepository memberJPARepository;
	@Mock private BookJPARepository bookJPARepository;
	@Mock private LibraryBookPropertiesHolder libraryBookPropertiesHolder;
	@InjectMocks private LibraryServiceImpl libraryService;

	@Test
	public void should_GetBorrowedBook_ByMemberName_Successfully() {
		when(memberJPARepository.findByName(MEMBER_NAME))
				.thenReturn(Optional.of(MEMBER_ENTITY));
		when(bookMapper.toBookReadPojo(BOOK_ENTITY))
				.thenReturn(BOOK_READ_POJO);

		final var result = libraryService.getBorrowedBooksByMember(MEMBER_NAME);
		assertNotNull(result);
		assertEquals(List.of(BOOK_READ_POJO), result);

		verify(memberJPARepository, only()).findByName(MEMBER_NAME);
		verify(bookMapper, atLeast(1)).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_GetBorrowedBook_ByMemberName_and_ReturnEmptyList() {
		when(memberJPARepository.findByName(MEMBER_NAME))
				.thenReturn(Optional.of(MEMBER_WITHOUT_BOOKS));

		final var result = libraryService.getBorrowedBooksByMember(MEMBER_NAME);
		assertNotNull(result);
		assertTrue(result.isEmpty());

		verify(memberJPARepository, only()).findByName(MEMBER_NAME);
	}

	@Test
	public void should_GetBorrowedBook_ByTitle_Successfully() {
		when(memberJPARepository.findByBorrowedBooksIsNotEmptyAndBorrowedBooksTitle(BOOK_TITLE))
				.thenReturn(MEMBERS);
		when(bookMapper.toBookReadPojo(BOOK_ENTITY))
				.thenReturn(BOOK_READ_POJO);

		final var result = libraryService.getBorrowedBooksByTitle(BOOK_TITLE);
		assertNotNull(result);
		assertEquals(List.of(BOOK_READ_POJO), result);

		verify(memberJPARepository, only()).findByBorrowedBooksIsNotEmptyAndBorrowedBooksTitle(BOOK_TITLE);
		verify(bookMapper, atLeast(1)).toBookReadPojo(BOOK_ENTITY);
	}

	@Test
	public void should_GetBorrowedBook_ByTitle_and_ReturnEmptyList() {
		when(memberJPARepository.findByBorrowedBooksIsNotEmptyAndBorrowedBooksTitle(BOOK_TITLE))
				.thenReturn(List.of());

		final var result = libraryService.getBorrowedBooksByTitle(BOOK_TITLE);
		assertNotNull(result);
		assertTrue(result.isEmpty());

		verify(memberJPARepository, only()).findByBorrowedBooksIsNotEmptyAndBorrowedBooksTitle(BOOK_TITLE);
	}

	@Test
	public void should_BorrowBook_Successfully() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.of(MEMBER_ENTITY_TO_BORROW_BOOK));
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.of(BOOK_ENTITY));
		when(libraryBookPropertiesHolder.getBorrowLimit())
				.thenReturn(BORROW_LIMIT);
		when(memberJPARepository.save(MEMBER_ENTITY_TO_BORROW_BOOK))
				.thenReturn(MEMBER_ENTITY);
		when(bookJPARepository.save(BOOK_ENTITY))
				.thenReturn(BOOK_ENTITY);
		when(memberMapper.toMemberReadPojo(MEMBER_ENTITY_TO_BORROW_BOOK))
				.thenReturn(MEMBER_READ_POJO);

		final var result = libraryService.borrowBook(MEMBER_ID, BOOK_ID);
		assertNotNull(result);
		assertEquals(MEMBER_READ_POJO, result);

		verify(memberJPARepository, times(1)).findById(MEMBER_ID);
		verify(bookJPARepository, times(1)).findById(BOOK_ID);
		verify(libraryBookPropertiesHolder, only()).getBorrowLimit();
		verify(memberJPARepository, times(1)).save(MEMBER_ENTITY_TO_BORROW_BOOK);
		verify(bookJPARepository, times(1)).save(BOOK_ENTITY);
		verify(memberMapper, only()).toMemberReadPojo(MEMBER_ENTITY_TO_BORROW_BOOK);
	}

	@Test
	public void should_BorrowBook_and_ThrowLibraryBadRequestException_DueToZeroBookAmount() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.of(MEMBER_ENTITY));
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.of(BOOK_ENTITY_WITH_ZERO_AMOUNT));

		assertThrows(LibraryBadRequestException.class,
				() -> libraryService.borrowBook(MEMBER_ID, BOOK_ID));

		verify(memberJPARepository, times(1)).findById(MEMBER_ID);
		verify(bookJPARepository, times(1)).findById(BOOK_ID);
	}

	@Test
	public void should_BorrowBook_and_ThrowLibraryBadRequestException_DueToLimitBorrowBookAtOneMember() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.of(MEMBER_ENTITY_WITH_LIMIT_BORROWED_BOOKS));
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.of(BOOK_ENTITY));
		when(libraryBookPropertiesHolder.getBorrowLimit())
				.thenReturn(BORROW_LIMIT);

		assertThrows(LibraryBadRequestException.class,
				() -> libraryService.borrowBook(MEMBER_ID, BOOK_ID));

		verify(memberJPARepository, times(1)).findById(MEMBER_ID);
		verify(bookJPARepository, times(1)).findById(BOOK_ID);
		verify(libraryBookPropertiesHolder, only()).getBorrowLimit();
	}

	@Test
	public void should_ReturnBook_Successfully() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.of(MEMBER_ENTITY));
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.of(BOOK_ENTITY));
		when(memberJPARepository.save(MEMBER_ENTITY))
				.thenReturn(MEMBER_ENTITY);
		when(bookJPARepository.save(BOOK_ENTITY))
				.thenReturn(BOOK_ENTITY);
		when(memberMapper.toMemberReadPojo(MEMBER_ENTITY))
				.thenReturn(MEMBER_READ_POJO);

		final var result = libraryService.returnBook(MEMBER_ID, BOOK_ID);
		assertNotNull(result);
		assertEquals(MEMBER_READ_POJO, result);

		verify(memberJPARepository, times(1)).findById(MEMBER_ID);
		verify(bookJPARepository, times(1)).findById(BOOK_ID);
		verify(memberJPARepository, times(1)).save(MEMBER_ENTITY);
		verify(bookJPARepository, times(1)).save(BOOK_ENTITY);
		verify(memberMapper, only()).toMemberReadPojo(MEMBER_ENTITY);
	}

	@Test
	public void should_ReturnBook_and_ThrowLibraryBadRequestException_DueToAbsentSpecifiedBookAtMember() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.of(MEMBER_ENTITY_WITH_WRONG_BOOK));
		when(bookJPARepository.findById(BOOK_ID))
				.thenReturn(Optional.of(BOOK_ENTITY));

		assertThrows(LibraryBadRequestException.class,
				() -> libraryService.returnBook(MEMBER_ID, BOOK_ID));

		verify(memberJPARepository, times(1)).findById(MEMBER_ID);
		verify(bookJPARepository, times(1)).findById(BOOK_ID);
	}
}
