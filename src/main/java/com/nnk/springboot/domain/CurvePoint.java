package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "curve_point")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields

    /**
     *  => Mapping DONE according to the SQL and Java conventions
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "curve_id")
    private Integer curveId;
    @Column(name = "as_of_date")
    private Timestamp asOfDate;
    @Column(name = "term")
    private Double term;
    @Column(name = "value")
    private Double value;
    @Column(name = "creation_date")
    private Timestamp creationDate;

    public CurvePoint() {

    }

    public CurvePoint(int i, double v, double v1) {
    }
}
