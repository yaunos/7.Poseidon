package com.nnk.springboot.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")
    private Integer id;
    @Column(name="account")
    private String account;
    @Column(name="type")
    private String type;
    @Column(name="bidQuantity")
    private Double bidQuantity;
    @Column(name="askQuantity")
    private Double askQuantity;
    @Column(name="bid")
    private Double bid;
    @Column(name="ask")
    private Double ask;
    @Column(name="benchmark")
    private String benchmark;
    @Column(name="bidListDate")
    private Timestamp bidListDate;
    @Column(name="commentary")
    private String commentary;
    @Column(name="security")
    private String security;
    @Column(name="status")
    private String status;
    @Column(name="trader")
    private String trader;
    @Column(name="book")
    private String book;
    @Column(name="creationName")
    private String creationName;
    @Column(name="creationDate")
    private Timestamp creationDate;
    @Column(name="revisionName")
    private String revisionName;
    @Column(name="revisionDate")
    private Timestamp revisionDate;
    @Column(name="dealName")
    private String dealName;
    @Column(name="dealType")
    private String dealType;
    @Column(name="sourceListId")
    private String sourceListId;
    @Column(name="side")
    private String side;

}
