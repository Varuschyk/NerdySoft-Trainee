package com.nerdysoft.apicore.persistence.repository;

import com.nerdysoft.apicore.persistence.entity.MemberEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberJPARepository extends JpaRepository<MemberEntity, Long> {

	@Modifying
	@Query(value = """
        UPDATE MemberEntity m
        SET m.name = :name
        WHERE m.id = :id
        """, nativeQuery = true)
	Optional<MemberEntity> updateMemberById(@Nonnull @Param("id") final Long id,
																					@Nonnull @Param("name") final String name);
}
