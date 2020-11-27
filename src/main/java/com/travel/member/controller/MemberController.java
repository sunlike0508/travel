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

    @GetMapping("/login/{memberId}")
    public ResponseEntity<Member> loginMember(@PathVariable("memberId") String memberId, @RequestParam String password) {

        Member member = memberService.findByMemberIdAndPassword(memberId, password);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Member> joinMember(@RequestBody Member member) {

        memberService.joinMember(member);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    // 친구 ID 찾기
    @GetMapping("/search/{memberId}")
    public ResponseEntity<String> searchMember(@PathVariable("memberId") String memberId) {

        String findMemberId = memberService.findByMemberId(memberId);

        return new ResponseEntity<>(findMemberId, HttpStatus.OK);
    }
}
