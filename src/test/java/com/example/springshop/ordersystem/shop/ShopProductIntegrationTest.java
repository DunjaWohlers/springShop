package com.example.springshop.ordersystem.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ShopProductIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getOneProduct() throws Exception {
        mockMvc.perform(
                        get("/api/products/3")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                "id": 3,
                                "name":  "Zitrone"}
                                """
                ));
    }

    @Test
    void getAllProducts() throws Exception {
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
