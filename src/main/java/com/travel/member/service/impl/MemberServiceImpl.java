package com.travel.member.service.impl;

import com.travel.member.model.Member;
import com.travel.member.repository.MemberRepository;
import com.travel.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findByMemberIdAndPassword(String memberId, String password) {
        return memberRepository.findByMemberIdAndPassword(memberId, password);
    }

    @Override
    public void joinMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public String findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId).getMemberId();
    }
}
