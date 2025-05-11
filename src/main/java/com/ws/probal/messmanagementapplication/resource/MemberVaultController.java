package com.ws.probal.messmanagementapplication.resource;


import com.ws.probal.messmanagementapplication.domain.entity.MemberVault;
import com.ws.probal.messmanagementapplication.domain.request.AddAmountRequest;
import com.ws.probal.messmanagementapplication.service.MemberVaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vault")
public class MemberVaultController {

    private final MemberVaultService memberVaultService;

    @PostMapping("/add")
    public ResponseEntity<MemberVault> addAmountInVault(@RequestBody AddAmountRequest request) {
        MemberVault memberVault = memberVaultService.addAmountInVault(request);
        return ResponseEntity.ok(memberVault);
    }
}
