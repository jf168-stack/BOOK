package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book bok);

    public int deleteBook(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBook();

    int queryPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);
}
