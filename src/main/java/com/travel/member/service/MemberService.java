package com.travel.member.service;

import com.travel.member.model.Member;

public interface MemberService {
    Member findByMemberId(String memberId);
}
