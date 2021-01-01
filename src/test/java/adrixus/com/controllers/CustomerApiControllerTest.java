package adrixus.com.controllers;

import adrixus.com.app.AdrixusApiApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import junit.framework.TestCase;
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
public class CustomerApiControllerTest  {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*********************   ADD NEW CUSTOMER *********************************************/

    @Test
    public void addCustomer_Name_Not_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.set("name",objectMapper.valueToTree(null));
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/customer/add-customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide name")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void addCustomer_Country_Not_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("name","Adrixus");
        dataNode.set("countryId",objectMapper.valueToTree(null));
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/customer/add-customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide country")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void addCustomer_State_Not_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("name","Adrixus");
        dataNode.put("countryId",101);
        dataNode.set("stateId",objectMapper.valueToTree(null));
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/customer/add-customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide state")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void addCustomer_City_Not_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("name","Adrixus");
        dataNode.put("countryId",101);
        dataNode.put("stateId",4030);
        dataNode.set("cityId",objectMapper.valueToTree(null));
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/customer/add-customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide city")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void addCustomer_All_Input()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("name","Adrixus");
        dataNode.put("countryId",101);
        dataNode.put("stateId",4030);
        dataNode.put("cityId",57606);
        dataNode.put("addressLine1","143 Bay Store");
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/customer/add-customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",hasKey("customerId")))
                .andExpect(jsonPath("$.error",is(false)));
    }

    /*********************   GET  CUSTOMERS *********************************************/

    @Test
    public void getCustomers_No_Index_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("itemsPerIndex",10);
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/customer/get-customers")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide index")))
                .andExpect(jsonPath("$.error",is(true)));
    }

    @Test
    public void getCustomers_No_Items_Per_Index_Given()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("index",0);
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/customer/get-customers")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data",is("Please provide itemsPerIndex")))
                .andExpect(jsonPath("$.error",is(true)));
    }


    @Test
    public void getCustomers()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("index",0);
        dataNode.put("itemsPerIndex",10);
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/customer/get-customers")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.error",is(false)));
    }

    @Test
    public void getCustomers_No_Data()throws Exception{
        ObjectNode rootNode  =objectMapper.createObjectNode();
        ObjectNode dataNode  =objectMapper.createObjectNode();
        dataNode.put("index",10);
        dataNode.put("itemsPerIndex",10);
        rootNode.set("data",dataNode);
        mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/customer/get-customers")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rootNode))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.error",is(false)));
    }
}