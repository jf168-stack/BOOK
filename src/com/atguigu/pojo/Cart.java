package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车模型
 *
 * @author jiangfeng
 */
public class Cart {
    /**
     * 购物车商品
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        // 查看购物车中国是否有该商品，有就数量增加，没有则添加到购物车
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            // 不存在该商品，加入购物车
            items.put(cartItem.getId(), cartItem);
        } else {
            // 存在该商品，数量增加
            item.setCount(item.getCount() + cartItem.getCount());
            // 该商品的总价重新计算
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clearCart() {
        items.clear();
    }

    /**
     * 更新商品数量
     *
     * @param id
     * @param count
     */
    public void updateItem(Integer id, Integer count) {
        CartItem item = items.get(id);
        if (item != null) {
            // 更新商品数量
            item.setCount(count);
            // 该商品的总价重新计算
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        // 遍历购物车计算商品总数
        for (Map.Entry<Integer, CartItem> item : items.entrySet()) {
            totalCount += item.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        // 遍历购物车计算总金额
        for (Map.Entry<Integer, CartItem> item : items.entrySet()) {
            totalPrice = totalPrice.add(item.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
