package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RuleNameControllerTest {

    @Autowired
    private WebApplicationContext webapp;

    private MockMvc mockMvc;

    @Autowired
    public RuleNameRepository ruleNameRepository;

    private MockHttpSession session;

    @Before
    public void setup() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webapp).build();

        //Mock a session with a valid user
        ResultActions auth =this.mockMvc.perform(MockMvcRequestBuilders.post("/login/authenticate")
                .param("admin", "admin"));
        MvcResult result = auth.andReturn();
        session = (MockHttpSession)result.getRequest().getSession();
    }


    // List rule name (GET)

    @Test
    public void RuleNameListTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/ruleName/list").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"));
    }

    // Add (GET)

    @Test
    public void RuleNameAddTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/ruleName/add").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    // Add (POST)

    @Test
    public void RuleNameValidateTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/ruleName/validate").session(session)
                .param("ruleName.name","name")
                .param("ruleName.description","description")
                .param("ruleName.json","json")
                .param("ruleName.template","template");

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound());

    }


    // Update (GET)

    @Test
    public void RuleNameUpdateTest() throws Exception {

        RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rule = ruleNameRepository.save(rule);
        Integer id = rule.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/ruleName/update/"+id).session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));

        ruleNameRepository.deleteById(id);

    }

    // Update (POST)

    @Test
    public void RuleNameUpdatePostTest() throws Exception {

        RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rule = ruleNameRepository.save(rule);
        Integer id = rule.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/ruleName/update/"+id).session(session)
                .param("ruleName.name","name")
                .param("ruleName.description","description")
                .param("ruleName.json","json")
                .param("ruleName.template","template");

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound());

        ruleNameRepository.deleteById(id);

    }


    // Delete

    @Test
    public void RuleNameDeleteTest() throws Exception {

        RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rule = ruleNameRepository.save(rule);
        Integer id = rule.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/ruleName/delete/"+id).session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print());

        Optional<RuleName> ruleNameList = ruleNameRepository.findById(id);
        Assert.assertFalse(ruleNameList.isPresent());
    }
}
