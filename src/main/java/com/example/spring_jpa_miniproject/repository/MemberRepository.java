package com.example.spring_jpa_miniproject.repository;

import com.example.spring_jpa_miniproject.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

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
    public Boolean findByName(String name) {
        Boolean result = em.createQuery("select m from Member m where m.name =: name", Boolean.class)
                .setParameter("name", name)
                .getSingleResult();

        return result;
    }
}
