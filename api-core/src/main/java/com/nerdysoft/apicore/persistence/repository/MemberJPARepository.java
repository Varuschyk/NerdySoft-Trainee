package com.nerdysoft.apicore.persistence.repository;

import java.util.List;
import java.util.Optional;
import com.nerdysoft.apicore.persistence.entity.MemberEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of {@link MemberEntity}.
 */
@Repository
public interface MemberJPARepository extends JpaRepository<MemberEntity, Long> {
	Optional<MemberEntity> findByName(@Nonnull final String memberName);
	List<MemberEntity> findByBorrowedBooksIsNotEmptyAndBorrowedBooksTitle(@Nonnull final String title);
}
