package com.travel.member.repository;

import com.travel.member.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 맴버_저장_테스트 () {
        Member member = new Member();
        member.setName("신선호");
        member.setMemberId("sunlike0301" + LocalTime.now());
        member.setPassword("test1234");

        Member savedMember = memberRepository.save(member);

        assertThat(savedMember.getName(), is(member.getName()));
        assertThat(savedMember.getMemberId(), is(member.getMemberId()));
        assertThat(savedMember.getPassword(), is(member.getPassword()));
        assertNotNull(savedMember.getId());
        assertNotNull(savedMember.getCreatedDatetime());
    }

    @Test
    public void findAllByMemberId() {
        Member member = new Member();
        member.setName("신선호");
        member.setMemberId("sunlike0301" + LocalTime.now());
        member.setPassword("test1234");
        memberRepository.save(member);

        Member findedMember = memberRepository.findByMemberId(member.getMemberId());

        assertThat(findedMember.getName(), is(member.getName()));
        assertThat(findedMember.getMemberId(), is(member.getMemberId()));
        assertThat(findedMember.getPassword(), is(member.getPassword()));
        assertNotNull(findedMember.getId());
        assertNotNull(findedMember.getCreatedDatetime());
    }
}