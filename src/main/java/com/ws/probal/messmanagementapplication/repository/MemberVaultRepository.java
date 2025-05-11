package com.ws.probal.messmanagementapplication.repository;

import com.ws.probal.messmanagementapplication.domain.entity.MemberVault;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MemberVaultRepository extends JpaRepository<MemberVault, Long> {
    List<MemberVault> findAllByDateBetween(LocalDate start, LocalDate end);
}
