package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
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
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webapp;

    private MockMvc mockMvc;

    @Autowired
    public UserRepository userRepository;

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


    // List html page (GET)

    @Test
    public void UserListTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/user/list").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    // Add form info to be added to the html page(GET)

    @Test
    public void UserAddTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/user/add").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }


}