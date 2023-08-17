package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     *  READ (ALL) : Get all rule names from data repository
     *  Return a list of all rule names
     */
    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    /**
     * READ (ONE) : Find a rule name by id in all rule names from data source
     */
    public Optional<RuleName> getARuleNameByItsId(final Integer id) {
        return ruleNameRepository.findById(id);
    }

    /**
     * UPDATE/SAVE a rule name by id in all rule names from data source
     */
    public RuleName saveRuleName(RuleName ruleName) {
        RuleName savedRuleName = ruleNameRepository.save(ruleName);
        return savedRuleName;
    }


    /**
     * DELETE a rule name
     */
    public void deleteRuleName (final Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
