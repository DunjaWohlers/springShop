package com.example.springshop.ordersystem.shop;

import com.example.springshop.ordersystem.shop.order.Order;
import com.example.springshop.ordersystem.shop.order.OrderRepo;
import com.example.springshop.ordersystem.shop.product.Product;
import com.example.springshop.ordersystem.shop.product.ProductRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ShopServiceTest {

    @Test
    void getProduct() {
        ProductRepo productRepo= mock(ProductRepo.class);
        OrderRepo orderRepo=mock(OrderRepo.class);
        int productNumber=2;
        when(productRepo.getProduct(productNumber)).thenReturn(new Product(productNumber, "Banane"));
        ShopService shopService= new ShopService(productRepo,orderRepo);
        //when
        Product actual = shopService.getProduct(productNumber);
        //then
        assertThat(actual).isEqualTo(new Product(productNumber, "Banane"));
    }


    @Test
    void listProducts() {
        ProductRepo productRepo= mock(ProductRepo.class);
        OrderRepo orderRepo=mock(OrderRepo.class);
        when(productRepo.listProducts()).thenReturn( List.of(
                new Product(1, "Apfel"),
                new Product(2, "Banane"),
                new Product(3, "Zitrone"),
                new Product(4, "Mandarine")));
        ShopService shopService= new ShopService(productRepo,orderRepo);
        //when
        List<Product> actual = shopService.listProducts();

        List<Product> expected = List.of(
                new Product(1, "Apfel"),
                new Product(2, "Banane"),
                new Product(3, "Zitrone"),
                new Product(4, "Mandarine"));
        //then

       // assertThat(actual).hasSameElementsAs(expected).hasSize(expected.size());
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void addOrders() {
        ProductRepo productRepo= mock(ProductRepo.class);
        OrderRepo orderRepo=mock(OrderRepo.class);

        when(productRepo.getProduct(1)).thenReturn(new Product(1, "Apfel"));
        when(productRepo.getProduct(3)).thenReturn(new Product(3, "Zitrone"));
        when(productRepo.getProduct(4)).thenReturn(new Product(4, "Mandarine"));

       // when(orderRepo.addOrder(
        //        new Order(1,List.of( //????
        //            new Product(1, "Apfel"),
        //            new Product(3, "Zitrone"),
        //            new Product(4, "Mandarine")
        //        ))
        //);

        ShopService shopService= new ShopService(productRepo,orderRepo);
        //when
        shopService.addOrder(1,List.of(1,3,4));
        //verifizieren, ob Order an orderRepo übergeben wird

        //List<Order> actual = shopService.listOrders();
        Order expected = new Order(1, List.of(
                new Product(1, "Apfel"),
                new Product(3, "Zitrone"),
                new Product(4, "Mandarine")
        ));

        verify(orderRepo).addOrder(expected);
        //then

        // assertThat(actual).hasSameElementsAs(expected).hasSize(expected.size());
        //assertThat(actual).isEqualTo(expected);
    }
}