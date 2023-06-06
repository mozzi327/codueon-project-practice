package com.codueon.boostuppractice.domain.member.controller;

import com.codueon.boostuppractice.domain.member.dto.MemberDto;
import com.codueon.boostuppractice.domain.member.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<?> joinMember(@RequestBody @Valid MemberDto.Join joinMember) {
        joinService.joinMember(joinMember);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
