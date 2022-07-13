package com.example.springshop.ordersystem.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ShopObjectIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void putAndGetOneOrder() throws Exception {
        mockMvc.perform(
                        post("/api/orders/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        [1,2]
                                        """
                                )
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""));


        mockMvc.perform(
                        get("/api/orders/1")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                {"id": 1,
                "products":[
                                {
                                "id": 1,
                                "name":  "Apfel"},
                                {
                                "id": 2,
                                "name":  "Banane"}
                                ]
                     }
                                """
                ));

    }

    @Test
    void getAllOrders() throws Exception {

        mockMvc.perform(
                        post("/api/orders/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        [1,2,1]
                                        """
                                )
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mockMvc.perform(
                        post("/api/orders/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        [2,3,4,4]
                                        """
                                )
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""));


        mockMvc.perform(
                        get("/api/orders/")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
               [
                    {"id":1,"products":[
                            {"id":1,"name":"Apfel"},
                            {"id":2,"name":"Banane"},
                            {"id":1,"name":"Apfel"}
                    ]},
                    {"id":2,"products":[
                            {"id":2,"name":"Banane"},
                            {"id":3,"name":"Zitrone"},
                            {"id":4,"name":"Mandarine"},
                            {"id":4,"name":"Mandarine"}
                     ]}
                ]"""
                ));

        mockMvc.perform(
                        get("/api/products/")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                               [ {
                                "id": 1,
                                "name":  "Apfel"},
                                {
                                "id": 2,
                                "name":  "Banane"},
                                {
                                "id": 3,
                                "name":  "Zitrone"},
                                {
                                "id": 4,
                                "name":  "Mandarine"}
                                ]
                                """
                ));
    }





}
