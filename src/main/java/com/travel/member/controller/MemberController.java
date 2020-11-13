package com.travel.member.controller;

import com.travel.member.model.Member;
import com.travel.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable("memberId") String memberId) {

        Member member = memberService.findByMemberId(memberId);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
