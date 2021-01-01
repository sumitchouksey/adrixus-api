package adrixus.com.controllers;


import adrixus.com.app.AdrixusApiApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@ActiveProfiles("development")
@SpringBootTest(classes = AdrixusApiApplication.class)

public class CoreApiControllerTest  {


    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCountries()throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/core/get-countries")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data"). isNotEmpty())
                .andExpect(jsonPath("$.error",is(false)));
    }


    @Test
    public void getStates()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("countryId",101);
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/core/get-states")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data"). isNotEmpty())
                .andExpect(jsonPath("$.error",is(false)));
    }


    @Test
    public void getCities()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("stateId",4030);
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/core/get-cities")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data"). isNotEmpty())
                .andExpect(jsonPath("$.error",is(false)));
    }
}