package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取购物车对象
        Cart  cart = (Cart) req.getSession().getAttribute("cart");
        // 清空购物车
        if (cart!=null){
            cart.clearCart();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 删除购物车商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取商品项id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 从购物车中删除商品
        if (cart != null) {
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 添加商品到购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取商品id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 根据商品id查询图书信息
        Book book = bookService.queryBookById(id);
        // 将图书信息转化成CartItem对象
        CartItem cartItem = new CartItem(id, book.getName(), 1, book.getPrice(), book.getPrice());
        // 调用Cart.addItem()方法加入购物车
        // 使用同一辆购物车，判断session域中年是否有cart,有则直接添加，没有需要创建后添加到购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        // 重定向到商品页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
