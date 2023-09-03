package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields

    /**
     *  => Mapping DONE according to the SQL and Java conventions
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "moodys_rating")
    private String moodysRating;
    @Column(name = "sand_rating")
    private String sandRating;
    @Column(name = "fitch_rating")
    private String fitchRating;
    @Column(name = "order_number")
    private Integer orderNumber;

    public Rating() {
    }

    public Rating(String moodysRating, String sandRating, String fitchRating, int i) {
    }
}
