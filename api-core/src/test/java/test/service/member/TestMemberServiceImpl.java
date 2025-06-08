package test.service.member;

import com.nerdysoft.apicore.exception.member.MemberBadRequestException;
import com.nerdysoft.apicore.exception.member.MemberNotFoundException;
import com.nerdysoft.apicore.mapper.member.MemberMapper;
import com.nerdysoft.apicore.persistence.entity.BookEntity;
import com.nerdysoft.apicore.persistence.entity.MemberEntity;
import com.nerdysoft.apicore.persistence.repository.MemberJPARepository;
import com.nerdysoft.apicore.pojo.member.MemberReadPojo;
import com.nerdysoft.apicore.pojo.member.MemberWritePojo;
import com.nerdysoft.apicore.service.impl.MemberServiceImpl;
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
public class TestMemberServiceImpl {

	private static final BookEntity BOOK_ENTITY = BookEntity.builder().build();

	private static final Long MEMBER_ID = 1L;
	private static final String MEMBER_NAME = "Alexander";
	private static final Instant MEMBERSHIP_DATE = Instant.now();
	private static final List<BookEntity> BORROWED_BOOKS = List.of(BOOK_ENTITY);
	private static final MemberEntity MEMBER_ENTITY = MemberEntity
			.builder().id(MEMBER_ID).name(MEMBER_NAME).membershipDate(MEMBERSHIP_DATE)
			.borrowedBooks(List.of()).build();

	private static final MemberReadPojo MEMBER_READ_POJO = MemberReadPojo.builder()
			.name(MEMBER_NAME).membershipDate(MEMBERSHIP_DATE).build();
	private static final MemberWritePojo MEMBER_WRITE_POJO = MemberWritePojo.builder()
			.name(MEMBER_NAME).build();

	@Mock
	private MemberMapper memberMapper;
	@Mock
	private MemberJPARepository memberJPARepository;
	@InjectMocks
	private MemberServiceImpl memberService;

	@Test
	public void should_GetMember_ById_Successfully() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.of(MEMBER_ENTITY));
		when(memberMapper.toMemberReadPojo(MEMBER_ENTITY))
				.thenReturn(MEMBER_READ_POJO);

		final var result = memberService.get(MEMBER_ID);
		assertNotNull(result);
		assertEquals(MEMBER_READ_POJO, result);

		verify(memberJPARepository, only()).findById(MEMBER_ID);
		verify(memberMapper, only()).toMemberReadPojo(MEMBER_ENTITY);
	}

	@Test
	public void should_GetMember_ById_and_ThrowMemberNotFoundException() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.empty());

		assertThrows(MemberNotFoundException.class, () -> memberService.get(MEMBER_ID));

		verify(memberJPARepository, only()).findById(MEMBER_ID);
	}

	@Test
	public void should_CreateMember_Successfully() {
		when(memberMapper.toMemberEntity(MEMBER_WRITE_POJO))
				.thenReturn(MEMBER_ENTITY);
		when(memberJPARepository.save(MEMBER_ENTITY))
				.thenReturn(MEMBER_ENTITY);
		when(memberMapper.toMemberReadPojo(MEMBER_ENTITY))
				.thenReturn(MEMBER_READ_POJO);

		final var result = memberService.create(MEMBER_WRITE_POJO);
		assertNotNull(result);
		assertEquals(MEMBER_READ_POJO, result);

		verify(memberMapper, times(1)).toMemberEntity(MEMBER_WRITE_POJO);
		verify(memberJPARepository, only()).save(MEMBER_ENTITY);
		verify(memberMapper, times(1)).toMemberReadPojo(MEMBER_ENTITY);
	}

	@Test
	public void should_UpdateMember_Successfully() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.of(MEMBER_ENTITY));
		when(memberMapper.toMemberEntity(MEMBER_WRITE_POJO))
				.thenReturn(MEMBER_ENTITY);
		when(memberJPARepository.save(MEMBER_ENTITY))
				.thenReturn(MEMBER_ENTITY);
		when(memberMapper.toMemberReadPojo(MEMBER_ENTITY))
				.thenReturn(MEMBER_READ_POJO);

		final var result = memberService.update(MEMBER_ID, MEMBER_WRITE_POJO);
		assertNotNull(result);
		assertEquals(MEMBER_READ_POJO, result);

		verify(memberJPARepository, times(1)).findById(MEMBER_ID);
		verify(memberMapper, times(1)).toMemberEntity(MEMBER_WRITE_POJO);
		verify(memberJPARepository, times(1)).save(MEMBER_ENTITY);
		verify(memberMapper, times(1)).toMemberReadPojo(MEMBER_ENTITY);
	}

	@Test
	public void should_UpdateMember_and_ThrowMemberNotFoundException() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.empty());

		assertThrows(MemberNotFoundException.class, () -> memberService.update(MEMBER_ID, MEMBER_WRITE_POJO));

		verify(memberJPARepository, only()).findById(MEMBER_ID);
	}

	@Test
	public void should_DeleteMember_Successfully() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.of(MEMBER_ENTITY));
		doNothing().when(memberJPARepository).deleteById(MEMBER_ID);
		when(memberMapper.toMemberReadPojo(MEMBER_ENTITY))
				.thenReturn(MEMBER_READ_POJO);

		final var result = memberService.delete(MEMBER_ID);
		assertNotNull(result);
		assertEquals(MEMBER_READ_POJO, result);

		verify(memberJPARepository, times(1)).findById(MEMBER_ID);
		verify(memberMapper, only()).toMemberReadPojo(MEMBER_ENTITY);
		verify(memberJPARepository, times(1)).deleteById(MEMBER_ID);
	}

	@Test
	public void should_DeleteMember_ThrowMemberBadRequestException() {
		when(memberJPARepository.findById(MEMBER_ID))
				.thenReturn(Optional.of(MemberEntity.builder().borrowedBooks(BORROWED_BOOKS).build()));

		assertThrows(MemberBadRequestException.class, () -> memberService.delete(MEMBER_ID));

		verify(memberJPARepository, times(1)).findById(MEMBER_ID);
	}
}
