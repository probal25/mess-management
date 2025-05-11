package com.ws.probal.messmanagementapplication.repository;

import com.ws.probal.messmanagementapplication.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
