package com.example.springshop.ordersystem;

import com.example.springshop.ordersystem.shop.ShopService;
import com.example.springshop.ordersystem.shop.product.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class pathClass {

    private final ShopService shopService;

    public pathClass(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public List<Product> getProducts() {
        return shopService.listProducts();
    }

    @GetMapping(path="{id}")
    public Product getProduct(@PathVariable int id) {
        return shopService.getProduct(id);
    }



}




