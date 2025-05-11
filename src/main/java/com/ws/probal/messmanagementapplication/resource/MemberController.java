package com.ws.probal.messmanagementapplication.resource;

import com.ws.probal.messmanagementapplication.domain.entity.Member;
import com.ws.probal.messmanagementapplication.repository.MemberRepository;
import com.ws.probal.messmanagementapplication.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member addedMember = memberService.addMember(member);
        return ResponseEntity.ok(addedMember);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAll() {
        return ResponseEntity.ok(memberService.getAll());
    }
}
