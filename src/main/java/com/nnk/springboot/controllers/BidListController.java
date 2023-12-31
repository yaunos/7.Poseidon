package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {
    // TODO: Inject Bid service

    // => DONE with @Autowired

    @Autowired
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view

        // => DONE
        List<BidList> bidList = bidListService.getAllBids();
        model.addAttribute("bidLists", bidList);

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list

        // => DONE
        // if info is correct in the form
        if (!result.hasErrors()) {
            bidListService.saveBidList(bid);
            model.addAttribute("bidList", bidListService.getAllBids());

            return "redirect:/bidList/list";

        } else {
            // stay on the form
            return "bidList/add";

        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form

        // => DONE

        BidList bidList = bidListService.getABidListByItsId(id).orElseThrow(() -> new IllegalArgumentException("Bidlist number " + id + " doesn't exist"));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid

        // => DONE
        if (!result.hasErrors()) {
            bidList.setId(id);
            bidListService.saveBidList(bidList);
            model.addAttribute("bidList", bidListService.getAllBids());
           return "redirect:/bidList/list";
        } else {
            return "bidList/update";
        }
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list

        // => DONE
        bidListService.deleteBidList(id);

        return "redirect:/bidList/list";
    }
}
