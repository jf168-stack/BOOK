package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jiangfeng
 */
public class CartTest {
    public Cart initCart(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"王者荣耀",1,new BigDecimal(20),new BigDecimal(20)));
        cart.addItem(new CartItem(1,"王者荣耀",1,new BigDecimal(20),new BigDecimal(20)));
        cart.addItem(new CartItem(2,"消消乐",1,new BigDecimal(40),new BigDecimal(40)));
        return cart;
    }

    @Test
    public void addItem() {
        Cart cart = initCart();
        System.out.println("cart = " + cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = initCart();
        cart.deleteItem(1);
        System.out.println("cart = " + cart);
    }

    @Test
    public void clearCart() {
        Cart cart = initCart();
        cart.clearCart();
        System.out.println("cart = " + cart);
    }

    @Test
    public void updateItem() {
        Cart cart = initCart();
        cart.updateItem(2, 10);
        System.out.println("cart = " + cart);
    }
}