package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * READ (ALL) : Find all curve points from data source
     */
    public List<CurvePoint> getAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    /**
     * READ (ONE) : Find a curve point by id from all curve points from data source
     */
    public Optional<CurvePoint> getACurvePointByItsId(final Integer id) {
        return curvePointRepository.findById(id);
    }

    /**
     * UPDATE/SAVE a curvePoint by id from all bids from data source
     */
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
        return savedCurvePoint;
    }

    /**
     * DELETE a curve point
     */
    public void deleteCurvePoint(final Integer id) {
        curvePointRepository.deleteById(id);
    }
}
