package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "相信未来", "江南", new BigDecimal(222), 4000, 5000, null));
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(25);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(25, "相信未来", "姜枫", new BigDecimal(222), 4000, 5000, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(25));
    }

    @Test
    public void queryBook() {
        for (Book book : bookService.queryBook()) {
            System.out.println(book);
        }
    }
    @Test
    public void page() {
        System.out.println(bookService.page(1, 4).toString());
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, 4,10,50).toString());
    }
}