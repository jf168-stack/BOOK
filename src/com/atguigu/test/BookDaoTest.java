package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        Book book = new Book(null, "小疯子", "小枫", new BigDecimal(455), 22222, 0, null);
        System.out.println(book.toString());
        bookDao.addBook(book);
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBook(24);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(20, "小疯子", "小枫", new BigDecimal(455), 22222, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(20));
    }

    @Test
    public void queryBook() {
        for (Book book:bookDao.queryBook()) {
            System.out.println(book);
        }
    }
    @Test
    public void queryPageTotalCount() {
        System.out.println(bookDao.queryPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(0, 4)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryPageTotalCountByPrice() {
        System.out.println(bookDao.queryPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0, 4,10,50)) {
            System.out.println(book);
        }
    }
}