package com.nnk.springboot.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "json")
    private String json;
    @Column(name = "sqlStr")
    private String sqlStr;
    @Column(name = "sqlPart")
    private String sqlPart;

}
