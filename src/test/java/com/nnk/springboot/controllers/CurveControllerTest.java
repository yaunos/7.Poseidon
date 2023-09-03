package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurveControllerTest {

    @Autowired
    private WebApplicationContext webapp;

    private MockMvc mockMvc;

    @Autowired
    public CurvePointRepository curvePointRepository;

    private MockHttpSession session;

    @Before
    public void setup() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webapp).build();

        ResultActions auth =this.mockMvc.perform(MockMvcRequestBuilders.post("/login/authenticate")
                .param("admin", "admin"));
        MvcResult result = auth.andReturn();
        session = (MockHttpSession)result.getRequest().getSession();
    }


    // List (GET)

    @Test
    public void CurveListTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/curvePoint/list").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"));
    }

    // Add (GET)

    @Test
    public void CurveAddTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/curvePoint/add").session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    // Add (POST)

    @Test
    public void CurveValidateTest() throws Exception {

        RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/curvePoint/validate").session(session)
                .param("curvePoint.CurveId","5")
                .param("curvePoint.term","term")
                .param("curvePoint.value","4");

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound());
        ;

    }


    // Update (GET)

    @Test
    public void CurveUpdateTest() throws Exception {

        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);;
        curvePoint = curvePointRepository.save(curvePoint);
        Integer id = curvePoint.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/curvePoint/update/"+id).session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));

        curvePointRepository.deleteById(id);

    }

    // UPDATE PAGE (POST)
    @Test
    public void CurveUpdatePostTest() throws Exception {

        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
        curvePoint = curvePointRepository.save(curvePoint);
        Integer id = curvePoint.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/curvePoint/update/"+id).session(session)
                .param("curvePoint.CurveId","5")
                .param("curvePoint.term","6")
                .param("curvePoint.value","4");

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound());
                //.andExpect(view().name("curvePoint/list"));

        curvePointRepository.deleteById(id);

    }


    // DELETE

    @Test
    public void CurveDeleteTest() throws Exception {

        CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
        curvePoint = curvePointRepository.save(curvePoint);
        Integer id = curvePoint.getId();

        RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/curvePoint/delete/"+id).session(session);

        this.mockMvc.perform(echoUserReq)
                .andDo(MockMvcResultHandlers.print());

        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
        Assert.assertFalse(curvePointList.isPresent());
    }


}