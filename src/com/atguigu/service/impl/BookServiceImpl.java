package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBook() {
        return bookDao.queryBook();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        //获取总记录数
        int pageTotalCount = bookDao.queryPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //获取总页数
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页数
        page.setPageTotal(pageTotal);
        //设置当前页
        page.setPageNo(pageNo);
        //设置每页数据量
        page.setPageSize(pageSize);
        //获取当前页的索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //获取当前页显示的数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        //设置当前页显示的数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        //获取总记录数
        int pageTotalCount = bookDao.queryPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //获取总页数
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页数
        page.setPageTotal(pageTotal);
        //设置当前页
        page.setPageNo(pageNo);
        //设置每页数据量
        page.setPageSize(pageSize);
        //获取当前页的索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //获取当前页显示的数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        //设置当前页显示的数据
        page.setItems(items);
        return page;
    }
}
