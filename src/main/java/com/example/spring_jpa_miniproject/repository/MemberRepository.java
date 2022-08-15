package com.example.spring_jpa_miniproject.repository;

import com.example.spring_jpa_miniproject.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /**
     * 회원 저장
     * @param member
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * 회원 검색하기(id)
     *
     * @param id
     * @return Member.class
     */
    public Optional<Member> findOne(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    /**
     * 회원 검색하기(name)
     *
     * @param name
     * @return List<Member>
     */
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :userName", Member.class)
                .setParameter("userName", name)
                .getResultList();
    }
}
