package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링이 엔티티매니저 만들어서 여기에다 인젝션 해주게 됨
    private EntityManager em;

    // 저장
    public void save(Member member) {
        em.persist(member);
    }

    // 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id); // 첫 번째 파라미터는 타입, 두 번째 파라미터는 PK
    }

    // 리스트 조회 (ID)
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class) // 조회 타입은 Member.class
                .getResultList();
    }

    public List<Member> findName(String name){
        return em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
