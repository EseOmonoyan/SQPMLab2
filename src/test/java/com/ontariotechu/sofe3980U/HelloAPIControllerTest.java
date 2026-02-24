package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloAPIDefault() throws Exception {
        mockMvc.perform(get("/helloAPI"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    public void testHelloAPIWithName() throws Exception {
        mockMvc.perform(get("/helloAPI").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello John!"));
    }

    @Test
    public void testEmailAPIFullName() throws Exception {
        mockMvc.perform(get("/emailAPI").param("fname", "Abraham").param("lname", "Lincoln"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Abraham Lincoln"));
    }

    @Test
    public void testEmailAPIFirstOnly() throws Exception {
        mockMvc.perform(get("/emailAPI").param("fname", "Abraham"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Abraham"));
    }

    @Test
    public void testEmailAPILastOnly() throws Exception {
        mockMvc.perform(get("/emailAPI").param("lname", "Lincoln"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lincoln"));
    }

    @Test
    public void testEmailAPINoParams() throws Exception {
        mockMvc.perform(get("/emailAPI"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Unknown"));
    }
}
