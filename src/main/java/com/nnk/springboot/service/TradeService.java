package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    /**
     *  Get all trades from data repository
     *  Return a list of all trades
     */
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }


}
