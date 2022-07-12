package com.example.springshop.ordersystem.shop.order;


import com.example.springshop.ordersystem.shop.product.Product;

import java.util.List;

public record Order(
        int id,
        List<Product> products
) {
}