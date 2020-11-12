package com.meber.repository;

import com.member.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;


class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 맴버_저장_테스트 () {
        Member member = new Member();
        member.setMemberId("sunlike0301");
        member.setPassword("test1234");

        Member savedMember = memberRepository.save(member);

        System.out.println(savedMember.getId());
        System.out.println(savedMember.getMemberId());
        System.out.println(savedMember.getPassword());
        System.out.println(savedMember.getCreatedDatetime());

        assertThat(savedMember.getMemberId(), is(member.getMemberId()));
        assertThat(savedMember.getPassword(), is(member.getPassword()));
        assertNotNull(savedMember.getId());
        assertNotNull(savedMember.getCreatedDatetime());
    }

    @Test
    public void findAllByMemberId() {
        //Member expected
    }
}