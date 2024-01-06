package com.example.BookService;

import com.example.BookMapper.BookMapper;
import com.example.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Book book) {
        return bookMapper.insertBook(book);
    }

    public Book getBookById(int id) {
        return bookMapper.selectBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookMapper.selectAllBooks();
    }

    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    public void deleteBooks(List<Integer> ids) {
        bookMapper.deleteBooksByIds(ids);
    }
    }
