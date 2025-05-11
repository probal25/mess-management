package com.ws.probal.messmanagementapplication.service;


import com.ws.probal.messmanagementapplication.domain.entity.Member;
import com.ws.probal.messmanagementapplication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepo;

    public Member addMember(Member member) {
        return memberRepo.save(member);
    }

    public List<Member> getAll() {
        return memberRepo.findAll();
    }
}
