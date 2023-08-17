package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    /**
     *  READ (ALL) :Get all trades from data repository
     *  Return a list of all trades
     */
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    /**
     * READ (ONE) : Find a trade by id in all trades from data source
     */
    public Optional<Trade> getATradeByItsId(final Integer id) {
        return tradeRepository.findById(id);
    }

    /**
     * UPDATE/SAVE a trade by id in all bids from data source
     */
    public Trade saveTrade(Trade trade) {
        Trade savedTrade = tradeRepository.save(trade);
        return savedTrade;
    }

    /**
     * DELETE a trade
     */
    public void deleteTrade (final Integer id) {
        tradeRepository.deleteById(id);
    }

}
