package com.ws.probal.messmanagementapplication.service;

import com.ws.probal.messmanagementapplication.domain.entity.MemberVault;
import com.ws.probal.messmanagementapplication.domain.entity.Member;
import com.ws.probal.messmanagementapplication.domain.request.AddAmountRequest;
import com.ws.probal.messmanagementapplication.repository.MemberVaultRepository;
import com.ws.probal.messmanagementapplication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberVaultService {

    private final MemberVaultRepository memberVaultRepository;
    private final MemberRepository memberRepository;

    public MemberVault addAmountInVault(AddAmountRequest dto) {
        Member paidBy = memberRepository.findById(dto.getPaidById())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        MemberVault memberVault = new MemberVault(null,  dto.getAmount(), dto.getDate(), paidBy);
        return memberVaultRepository.save(memberVault);
    }
}
