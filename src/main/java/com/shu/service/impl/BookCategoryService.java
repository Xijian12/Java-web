package com.shu.service.impl;

import com.shu.mapper.BookCategoryMapper;
import com.shu.entity.vo.request.Book;
import com.shu.entity.vo.request.BookCategory;
import com.shu.entity.vo.request.BookDetail;
import com.shu.entity.vo.request.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryService {
    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    public BookCategory getCategoryById(int categoryId) {
        return bookCategoryMapper.selectCategoryById(categoryId);
    }

    public List<BookCategory> getAllCategories(int page,int pageSize) {
        int offset =(page-1)*pageSize;

        return bookCategoryMapper.selectAllCategories(offset,pageSize);
    }

    public void addCategory(String categoryName, String categoryAlias) {
        BookCategory category = new BookCategory();
        category.setCategoryName(categoryName);
        category.setCategoryAlias(categoryAlias);
        // 默认点击和下载次数为0
        category.setCategoryClickNum(0);
        category.setCategoryDownloadNum(0);
        bookCategoryMapper.insertCategory(category);
    }

    public void updateCategory(BookCategory category) {
        bookCategoryMapper.updateCategory(category);
    }

    public  void deleteCategories(List<Integer> categoryIds) {
        bookCategoryMapper.deleteCategoriesByIds(categoryIds);
    }
    public List<BookDetail> getBooksByCategoryId(Long categoryId) {
        // 实现从数据库获取该分类下所有图书信息的逻辑

        return  bookCategoryMapper.getBooksByCategoryId( categoryId);// 返回图书详情列表
    }





        public BookResponse getBooksByCategoryName(String categoryName, int page, int pageSize) {
            int offset = (page - 1) * pageSize;
            List<Book> book = bookCategoryMapper.getBooksByCategoryName(categoryName, pageSize, offset);
            int total = book.size();  // 本次查询到的图书总数

            BookResponse response = new BookResponse();
            response.setTotal(total);
            response.setItems(book);
            return response;
        }

    // 其他业务逻辑...
}