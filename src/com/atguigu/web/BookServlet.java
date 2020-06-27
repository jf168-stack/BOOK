package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        //获取提交的图书信息，封装成对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用servi|ce方法保存图书信息
        bookService.addBook(book);
        //请求重定向到图书列表
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取修改后的book数据，并封装成对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用service的update方法更新数据
        bookService.updateBook(book);
        //请求重定向到图书列表
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取删除图书的id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用service方法删除该图书
        bookService.deleteBook(id);
        //请求重定向到图书列表
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取全部图书信息
        List<Book> books = bookService.queryBook();
        //2.将图书信息放到request域中
        req.setAttribute("books", books);
        //3.请求转发到book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前页码和每页记录数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用BookService.page(pageNo,pageSize)返回Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //保存Page对象到request域中
        req.setAttribute("page", page);
        //请求转发到、page/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //根据图书编号查询对应图书
        Book book = bookService.queryBookById(id);
        //将图书信息放到request域中
        req.setAttribute("book", book);
        //请求转发到图书修改界面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }
}
