package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {
    // TODO: Inject Curve Point service

    // => DONE with @Autowired

    @Autowired
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model

        // => DONE
        List<CurvePoint> curvePoint = curvePointService.getAllCurvePoints();
        model.addAttribute("curvePoints", curvePoint);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list

        // => DONE
        // if info is correct in the form
        if (!result.hasErrors()) {
            curvePointService.saveCurvePoint(curvePoint);
            model.addAttribute("curvePoint", curvePointService.getAllCurvePoints());

            return "redirect:/curvePoint/list";

        } else {
            // stay on the form
            return "curvePoint/add";
        }
    }


    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form

        CurvePoint curvePoint = curvePointService.getACurvePointByItsId(id).orElseThrow(() -> new IllegalArgumentException("Bidlist number " + id + " doesn't exist"));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if(!result.hasErrors()) {
            curvePoint.setId(id);
            curvePointService.saveCurvePoint(curvePoint);
            model.addAttribute("curvePoint", curvePointService.getAllCurvePoints());
            return "redirect:/curvePoint/list";
        } else {
            return "redirect:/curvePoint/update";
        }
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list

        curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
