package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerTest {

    @Autowired
    private WebApplicationContext webapp;

    private MockMvc mockMvc;

    @Autowired
    private BidListRepository bidListRepository;

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


    // List (GET)

    @Test
    public void BidListListTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/bidList/list").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"));
    }

    // Add (GET)

    @Test
    public void BidListAddTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/bidList/add").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }



    // UPDATE PAGE (GET)

    @Test
    public void BidListUpdateTest() throws Exception {

        BidList bidList = new BidList("Account Test", "Type Test", 10d);
        bidList = bidListRepository.save(bidList);
        Integer id = bidList.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/bidList/update/"+id).session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));

        bidListRepository.deleteById(id);

    }


    // Delete

    @Test
    public void BidListDeleteTest() throws Exception {

        BidList bidList = new BidList("Account Test", "Type Test", 10d);
        bidList = bidListRepository.save(bidList);
        Integer id = bidList.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/bidList/delete/"+id).session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print());

        // Optional<BidList> bidList = bidListRepository.findById(id);
        // Assert.assertFalse(bidList.isPresent());
    }








}
