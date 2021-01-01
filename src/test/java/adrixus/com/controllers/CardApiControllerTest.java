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

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@ActiveProfiles("development")
@SpringBootTest(classes = AdrixusApiApplication.class)
public class CardApiControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    /*********************   ADD NEW CARD *********************************************/

    @Test
    public void addCard_CardNo_Not_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.set("cardNo",objectMapper.valueToTree(null));
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/cards/add-card")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide cardNo")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void addCard_CardType_Not_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("cardNo",1452256347858965L);
        dataNode.set("cardType",objectMapper.valueToTree(null));
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/cards/add-card")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide cardType")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void addCard_Cvv_Not_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("cardNo",1452256347858965L);
        dataNode.put("cardType","visa");
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/cards/add-card")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide cvv")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void addCard_Password_Not_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("cardNo",1452256347858965L);
        dataNode.put("cardType","visa");
        dataNode.put("cvc",2315);
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/cards/add-card")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide password")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void addCard_Customer_Not_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("cardNo",1452256347858965L);
        dataNode.put("cardType","visa");
        dataNode.put("cvc",231);
        dataNode.put("password",2315);
        dataNode.put("customerId",objectMapper.valueToTree(null));
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/cards/add-card")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide customerId")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void addCard_Customer_All_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("cardNo",1452256347858965L);
        dataNode.put("cardType","visa");
        dataNode.put("cvc",231);
        dataNode.put("password",2315);
        dataNode.put("customerId",37);
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/cards/add-card")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",hasKey("cardId")))
                .andExpect(jsonPath("$.error",is(false)));
    }

}