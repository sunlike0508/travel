package com.travel.member.controller;

import com.travel.member.model.Member;
import com.travel.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public Member getMember(@PathVariable("memberId") String memberId) {

        Member member = memberService.findByMemberId(memberId);

        return member;
    }
}
