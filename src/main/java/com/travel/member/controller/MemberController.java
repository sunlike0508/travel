package com.travel.member.controller;

import com.travel.member.model.Member;
import com.travel.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable("memberId") String memberId) {

        Member member = memberService.findByMemberId(memberId);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Member> joinMember(@RequestBody Member member) {

        memberService.joinMember(member);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
