package com.jpa.playground.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.sql.DataSourceDefinition;

public class Team {

    private String id;
    private String name;

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
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
}
