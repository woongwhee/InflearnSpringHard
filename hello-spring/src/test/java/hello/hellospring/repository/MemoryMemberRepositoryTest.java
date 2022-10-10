package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach//매 테스트 케이스가 끝날때마다 동작하는 콜백메소드
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member=new Member();
        member.setName("spring");
        repository.save(member);
        Member result=repository.findByName("spring").get();
//        Assertions.assertEquals(member,result);
        assertThat(member).isEqualTo(result);

    }
    @Test
    public void findByName(){

        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();//순서가 꼬여서 에러가난다.
        assertThat(result).isEqualTo(member1);

    }
    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
