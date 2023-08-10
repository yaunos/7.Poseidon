package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     *  Get all rule names from data repository
     *  Return a list of all rule names
     */
    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }
}
