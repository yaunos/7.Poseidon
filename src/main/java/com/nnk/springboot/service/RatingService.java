package com.nnk.springboot.service;


import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * READ (ALL) : Find all Rating from data source
     */
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    /**
     * DELETE a rating
     *
     */
    public void deleteRating(final Integer id) {
        ratingRepository.deleteById(id);
    }
}
