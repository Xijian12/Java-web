package com.example.BookService;

import com.example.BookMapper.BookCategoryMapper;
import com.example.pojo.BookCategory;
import com.example.pojo.BookDetail;
import com.example.pojo.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryService {
    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    public BookCategory getCategoryById(int id) {
        return bookCategoryMapper.selectCategoryById(id);
    }

    public List<BookCategory> getAllCategories() {
        return bookCategoryMapper.selectAllCategories();
    }

    public void addCategory(String name, String alias) {
        BookCategory category = new BookCategory();
        category.setName(name);
        category.setAlias(alias);
        // 默认点击和下载次数为0
        category.setClicks(0);
        category.setDownloads(0);
        bookCategoryMapper.insertCategory(category);
    }

    public void updateCategory(BookCategory category) {
        bookCategoryMapper.updateCategory(category);
    }

    public boolean deleteCategory(int id) {
        try {
            // 假设这是调用Mapper来删除分类的代码
             bookCategoryMapper.deleteCategory(id);
            // 返回true表示删除成功
            return true;
        } catch (Exception e) {
            // 处理异常，例如记录日志
            // 返回false表示删除失败
            return false;
        }
    }
    public List<BookDetail> getBooksByCategoryId(Long categoryId) {
        // 实现从数据库获取该分类下所有图书信息的逻辑

        return  bookCategoryMapper.getBooksByCategoryId( categoryId);// 返回图书详情列表
    }





        public BookResponse getBooksByCategoryName(String categoryName, int page, int pageSize) {
            int offset = (page - 1) * pageSize;
            List<BookDetail> bookDetails = bookCategoryMapper.getBooksByCategoryName(categoryName, pageSize, offset);
            int total = bookDetails.size();  // 本次查询到的图书总数

            BookResponse response = new BookResponse();
            response.setTotal(total);
            response.setItems(bookDetails);
            return response;
        }

    // 其他业务逻辑...
}