package com.jpa.playground;

import com.jpa.playground.model.entity.Member;
import com.jpa.playground.model.entity.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//@SpringBootApplication
public class PlaygroundApplication {

	public static void main(String[] args) {
		//		SpringApplication.run(PlaygroundApplication.class, args);
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
//		EntityManager em = emf.createEntityManager();	// 엔티티 매니저 생성
//
//		EntityTransaction tx = em.getTransaction();	// 트랜잭션 기능 획득
//
//		try {
//			System.out.println("안녕 세상아 내가 왔다!");
//		} catch (Exception e) {
//
//		} finally {
//
//		}

		// 순수 연관관계의 오브젝트
		Member member1 = new Member("member1", "회원1");
		Member member2 = new Member("member2", "회원2");
		Team team = new Team("team1", "팀1");

		member1.setTeam(team);
		member2.setTeam(team);

		Team findTeam = member2.getTeam();

		System.out.println(findTeam.getName());

	}

}
