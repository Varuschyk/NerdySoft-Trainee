package com.nerdysoft.apicore.persistence.repository;

import com.nerdysoft.apicore.persistence.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJPARepository extends JpaRepository<MemberEntity, Long> {
}
