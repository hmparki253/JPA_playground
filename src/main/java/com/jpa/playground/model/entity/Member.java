package com.jpa.playground.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public Member() {

    }

    public void setTeam(Team team) {
        // 사실 기존 팀과 관계를 제거해야한다
        if(this.team != null) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);    // 양방향을 교차로 세팅할 수 있도록
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }
}
