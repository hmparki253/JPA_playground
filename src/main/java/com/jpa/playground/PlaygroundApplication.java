package com.jpa.playground;

import com.jpa.playground.model.entity.Member;
import com.jpa.playground.model.entity.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

//@SpringBootApplication
public class PlaygroundApplication {

	public static void main(String[] args) {
		//		SpringApplication.run(PlaygroundApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();	// 엔티티 매니저 생성

		EntityTransaction tx = em.getTransaction();	// 트랜잭션 기능 획득

		try {
			tx.begin();
			bothBiDirectionalTwo(em);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();;

		// 순수 연관관계의 오브젝트

		// 기본적인 object의 연관관계
//		Member member1 = new Member("member1", "회원1");
//		Member member2 = new Member("member2", "회원2");
//		Team team = new Team("team1", "팀1");
//
//		member1.setTeam(team);
//		member2.setTeam(team);
//
//		Team findTeam = member2.getTeam();
//
//		System.out.println(findTeam.getName());

	}

	public static void testSave(EntityManager em) {

		//팀1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);

		// 회원1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1);
		em.persist(member1);

		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1);
		em.persist(member2);
	}

	private static void queryLogicJoin(EntityManager em) {
		String jpql = "select m from MEMBER m join m.team t where " +
				"t.name=:teamName"; // :teamName처럼 :로 시작하는 것은 파라미터를 바인딩 받는 문법

		List<Member> resultList = em.createQuery(jpql, Member.class)
				.setParameter("teamName", "팀1")
				.getResultList();

		for (Member member : resultList) {
			System.out.println("[query] member.username=" + member.getUsername());
		}
	}

	private static void updateRelation(EntityManager em) {

		// 새로운 팀2
		Team team2 = new Team("team2", "팀2");
		em.persist(team2);

		// 회원1에 새로운 팀2 설정
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(team2);

	}

	// 연관관계 제거
	private static void deleteRelation(EntityManager em) {
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(null);
	}

	// 양방향 연관관계
	private static void biDirection(EntityManager em) {
		Team team = em.find(Team.class, "team1");
		List<Member> members = team.getMembers();

		for(Member member: members) {
			System.out.println("member.username = " +
					member.getUsername());
		}
	}

	// 양방향 - 저장 (주인아닌 곳에 값 입력해서 저장시 문제)
	private static void testSaveNonOwner(EntityManager em) {
		Member member3 = new Member("member3", "회원3");
		em.persist(member3);

		Member member4 = new Member("member4", "회원4");
		em.persist(member4);

		Team team3 = new Team("team3", "팀3");

		team3.getMembers().add(member3);
		team3.getMembers().add(member4);

		em.persist(team3);

	}

	// 양방향의 결론: 양쪽 다 관계를 맺자
	private static void bothBiDirectional(EntityManager em) {
		Team team4 = new Team("team4", "팀4");
		em.persist(team4);

		Member member5 = new Member("member5", "회원5");
		member5.setTeam(team4);
		team4.getMembers().add(member5);
		em.persist(member5);

		Member member6 = new Member("member6", "회원6");
		member6.setTeam(team4);
		team4.getMembers().add(member6);
		em.persist(member6);
	}

	// 양방향의 결론: 양쪽 다 관계를 맺자, 리팩토링을 통해 주인이 아닌 곳에서 add를 자동으로
	private static void bothBiDirectionalTwo(EntityManager em) {
		Team team5 = new Team("team5", "팀5");
		em.persist(team5);

		Member member7 = new Member("member7", "회원7");
		member7.setTeam(team5);
		em.persist(member7);

		Member member8 = new Member("member8", "회원8");
		member8.setTeam(team5);
		em.persist(member8);
	}
}
