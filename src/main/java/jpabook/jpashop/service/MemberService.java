package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // final 붙은거에 생성자 만들어줌
//@Transactional // 데이터 변경하는 거는 무조건 트랙잭션이 있어야 한다.// 클래스에 Transactional 선언하면 기본적으로 public method 들은 트랙잭션에 들어간다.
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired // 생성자 하나만 있는 경우 Autowired 알아서 붙여줌
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /*
    * 회원 가입
    * */
    @Transactional // 쓰기에는 readOnly 옵션 넣으면 안됨 // readOnly 넣으면 변경이 안된다.
    public Long join(Member member){
        // 중복 회원인지 확인
        validateDuplicateMember(member);
        // 문제 없으면 저장 한 후
        memberRepository.save(member);
        // member에서 id 반환하기
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findName(member.getName());

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true) // 성능을 좋게 하기 위해 읽기에는 readOnly를 넣는게 좋다
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
