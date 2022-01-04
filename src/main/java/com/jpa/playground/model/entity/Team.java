package com.jpa.playground.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TEAM")
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String id;
    private String name;

    // 양방향 연관관계 세팅
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    public Team() {

    }

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team(String id, String name, List<Member> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
