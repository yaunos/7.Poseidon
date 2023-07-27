package com.nnk.springboot.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "CurveId")
    private Integer CurveId;
    @Column(name = "asOfDate")
    private Timestamp asOfDate;
    @Column(name = "term")
    private Double term;
    @Column(name = "value")
    private Double value;
    @Column(name = "creationDate")
    private Timestamp creationDate;

}
