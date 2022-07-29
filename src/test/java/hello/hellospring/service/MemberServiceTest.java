package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.ManagedArray;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveID = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveID).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        // given
        Member member01 = new Member();
        member01.setName("spring");

        Member member02 = new Member();
        member02.setName("spring");

        // when
        memberService.join(member01);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member02));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
       /* try {
            memberService.join(member02);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}