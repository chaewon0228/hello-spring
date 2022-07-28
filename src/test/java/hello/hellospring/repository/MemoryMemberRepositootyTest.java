package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sun.awt.geom.AreaOp;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositootyTest {
    MemberRepository repository = new MemoryMemberRepository();

    // 테스트는 독리적으로 실행되어야 한다.
    // 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
    // 메모리 DB의 직전 테스트 데이터를 삭제
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // 저장한 member 객체의 값과 같다면 true
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member01 = new Member();
        member01.setName("spring01");
        repository.save(member01);

        Member member02 = new Member();
        member02.setName("spring02");
        repository.save(member02);

        // when
        Member result = repository.findByName("spirng01").get();

        // then
        assertThat(result).isEqualTo(member01);
    }

    @Test
    public void findAll() {
        // given
        Member member01 = new Member();
        member01.setName("spring01");
        repository.save(member01);

        Member member02 = new Member();
        member02.setName("spirng02");
        repository.save(member02);

        // when
        List<Member> result = repository.findAll();

        // then : list의 사이즈(개수)
        assertThat(result.size()).isEqualTo(2);
    }
}
