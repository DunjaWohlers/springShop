package com.example.springshop.ordersystem;


import com.example.springshop.ordersystem.shop.ShopService;
import com.example.springshop.ordersystem.shop.order.Order;
import com.example.springshop.ordersystem.shop.order.OrderStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class orderClass {
    int counter=0;

    private final ShopService shopService;

    public orderClass(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public List<Order> getOrders() {
        return shopService.listOrders();
    }

    @GetMapping(path="{id}")
    public Order getOrder(@PathVariable int id) {
        return shopService.getOrder(id);
    }
    @PostMapping()
    public void setOrders(@RequestBody List<Integer> idS) {
        counter++;
        System.out.println(counter);
        OrderStatus orderStatus=OrderStatus.IN_PROGRESS;
        shopService.addOrder(counter,idS , orderStatus);
    }


}