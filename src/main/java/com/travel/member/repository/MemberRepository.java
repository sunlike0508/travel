package com.travel.member.repository;

import com.travel.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberIdAndPassword(String memberId, String password);

    Member findByMemberId(String memberId);
}
