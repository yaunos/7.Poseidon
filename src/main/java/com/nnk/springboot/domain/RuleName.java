package com.nnk.springboot.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rule_name")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields

    /**
     *  => Mapping DONE according to the SQL and Java conventions
     */

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
    @Column(name = "template")
    private String template;
    @Column(name = "sql_str")
    private String sqlStr;
    @Column(name = "sql_part")
    private String sqlPart;

    public RuleName() {
    }

    public RuleName(String ruleName, String description, String json, String template, String sql, String sqlPart) {
    }
}
