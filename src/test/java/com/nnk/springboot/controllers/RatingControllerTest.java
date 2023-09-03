package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
public class RatingControllerTest {

    @Autowired
    private WebApplicationContext webapp;

    private MockMvc mockMvc;

    @Autowired
    public RatingRepository ratingRepository;

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


    // List rating (GET)

    @Test
    public void RatingListTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/rating/list").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"));
    }

    // Add rating (GET)

    @Test
    public void RatingtAddTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/rating/add").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    // ADD PAGE (POST)

    @Test
    public void RatingValidateTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/rating/validate").session(session)
                .param("rating.moodysRating","moodys Rating")
                .param("rating.sandRating","sand Rating")
                .param("rating.fitchRating","fitch Rating");

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound());

    }


    // UPDATE PAGE (GET)

    @Test
    public void RatingUpdateTest() throws Exception {

        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating = ratingRepository.save(rating);
        Integer id = rating.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/rating/update/"+id).session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));

        ratingRepository.deleteById(id);

    }

    // UPDATE PAGE (POST)

    @Test
    public void RatingUpdatePostTest() throws Exception {

        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating = ratingRepository.save(rating);
        Integer id = rating.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/rating/update/"+id).session(session)
                .param("rating.moodysRating","moodys Rating")
                .param("rating.sandRating","sand Rating")
                .param("rating.fitchRating","fitch Rating");

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound());

        ratingRepository.deleteById(id);

    }


    // DELETE

    @Test
    public void RatingDeleteTest() throws Exception {

        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating = ratingRepository.save(rating);
        Integer id = rating.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/rating/delete/"+id).session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print());

        Optional<Rating> ratingList = ratingRepository.findById(id);
        Assert.assertFalse(ratingList.isPresent());
    }
}