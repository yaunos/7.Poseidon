package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
    }

    /**
     * READ (ALL) : Find all BidLists from data source
     */
    public List<BidList> getAllBids() {
        return bidListRepository.findAll();
    }

    /**
     * READ (ONE) : Find a bidList by id from all bids from data source
     */
    public Optional<BidList> getABidListByItsId(final Integer id) {
        return bidListRepository.findById(id);
    }

    /**
     * UPDATE/SAVE a bidList by id from all bids from data source
     */
    public BidList saveBidList(BidList bidList) {
        BidList savedBidList = bidListRepository.save(bidList);
        return savedBidList;
    }

    /**
     * DELETE a bidList
     */
    public void deleteBidList(final Integer id) {
        bidListRepository.deleteById(id);
    }

}
