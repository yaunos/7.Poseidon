package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class RuleNameController {
    // TODO: Inject RuleName service

    // => DONE with @Autowired

    @Autowired
    private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        // => DONE
        //model.addAttribute("ruleName", ruleNameService.getAllRuleNames());
        List<RuleName> ruleName = ruleNameService.getAllRuleNames();
        model.addAttribute("ruleName", ruleName);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        if (!result.hasErrors()) {
            ruleNameService.saveRuleName(ruleName);
            model.addAttribute("ruleName", ruleNameService.getAllRuleNames());

            return "redirect:/ruleName/list";

        } else {

            return "ruleName/add";
        }
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form

        // => DONE
        RuleName ruleName = ruleNameService.getARuleNameByItsId(id).orElseThrow(() -> new IllegalArgumentException("Rulename number " + id + " doesn't exist"));
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list

        // => DONE
        if (!result.hasErrors()) {
            ruleName.setId(id);
            ruleNameService.saveRuleName(ruleName);
            model.addAttribute("trade", ruleNameService.getAllRuleNames());
            return "redirect:/ruleName/list";
        } else {
            return "redirect:/ruleName/update";
        }
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list

        // => DONE
        ruleNameService.deleteRuleName(id);

        return "redirect:/ruleName/list";
    }
}
