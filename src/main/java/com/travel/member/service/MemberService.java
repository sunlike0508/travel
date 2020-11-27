package com.travel.member.service;

import com.travel.member.model.Member;

public interface MemberService {
    Member findByMemberIdAndPassword(String memberId, String password);

    void joinMember(Member member);

    String findByMemberId(String memberId);
}
