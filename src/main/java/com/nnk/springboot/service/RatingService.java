package com.nnk.springboot.service;


import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * READ (ONE) : Find a rating by id from all ratings from data source
     */
    public Optional<Rating> getARatingByItsId(final Integer id) {
        return ratingRepository.findById(id);
    }

    /**
     * UPDATE/SAVE a rating by id from all ratings from data source
     */
    public Rating saveRating(Rating rating) {
        Rating savedRating = ratingRepository.save(rating);
        return savedRating;
    }

    /**
     * DELETE a rating
     *
     */
    public void deleteRating(final Integer id) {
        ratingRepository.deleteById(id);
    }
}
