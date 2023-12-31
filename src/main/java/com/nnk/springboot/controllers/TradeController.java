package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.*;
import java.util.List;


@Controller
public class TradeController {
    // TODO: Inject Trade service

    // => DONE with @Autowired

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model

        // => DONE
        List<Trade> trade = tradeService.getAllTrades();
        model.addAttribute("trade", trade);

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade trade) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list

        if (!result.hasErrors()) {
            tradeService.saveTrade(trade);
            model.addAttribute("trade", tradeService.getAllTrades());

            return "redirect:/trade/list";

        } else {

            return "trade/add";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form

        // => DONE
        Trade trade = tradeService.getATradeByItsId(id).orElseThrow(() -> new IllegalArgumentException("Trade number " + id + " doesn't exist"));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list

        // => DONE
        if (!result.hasErrors()) {
            trade.setId(id);
            tradeService.saveTrade(trade);
            model.addAttribute("trade", tradeService.getAllTrades());
            return "redirect:/trade/list";
        } else {
            return "redirect:/trade/update";
        }
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list

        // => DONE
        tradeService.deleteTrade(id);

        return "redirect:/trade/list";
    }
}
