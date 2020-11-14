package com.travel.member.service;

import com.travel.member.model.Member;
import com.travel.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findByMemberId() {

        Member givenMember = new Member();
        givenMember.setName("신선호");
        givenMember.setMemberId("sunlike0301" + LocalTime.now());
        givenMember.setPassword("password");
        memberRepository.save(givenMember);

        Member expectedMember
                = memberService.findByMemberIdAndPassword(givenMember.getMemberId(), givenMember.getPassword());

        assertThat(expectedMember.getName(), is(givenMember.getName()));
        assertThat(expectedMember.getMemberId(), is(givenMember.getMemberId()));
        assertThat(expectedMember.getPassword(), is(givenMember.getPassword()));
    }
}