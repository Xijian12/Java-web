package com.example.BookMapper;

import com.example.pojo.BookCategory;
import com.example.pojo.BookDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookCategoryMapper {

    @Select("SELECT * FROM bookdemo.book_categories WHERE id = #{id}")
    BookCategory selectCategoryById(int id);

    @Select("SELECT * FROM example.book_categories")
    List<BookCategory> selectAllCategories();


    @Update("UPDATE example.book_categories SET name = #{name}, clicks = #{clicks}, downloads = #{downloads} WHERE id = #{id}")
    void updateCategory(BookCategory category);

    @Delete("DELETE FROM example.book_categories WHERE id = #{id}")
    void deleteCategory(int id);
    @Insert("INSERT INTO example.book_categories (name, alias, clicks, downloads) VALUES (#{name}, #{alias}, #{clicks}, #{downloads})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertCategory(BookCategory category);
    @Select("SELECT b.book_id, b.book_name, c.name as categoryName " +
            "FROM books b " +
            "JOIN book_categories c ON b.category_id = c.id " +
            "WHERE b.category_id = #{categoryId}")
    @Results({
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "bookName", column = "book_name"),
            @Result(property = "categoryName", column = "categoryName")
    })
    List<BookDetail> getBooksByCategoryId(@Param("categoryId") long categoryId);

    // 其他与图书分类相关的数据库操作...
    @Select("SELECT b.book_id, b.book_name, b.book_author, b.book_version, b.book_cover_url, c.name as categoryName " +
            "FROM books b " +
            "JOIN book_categories c ON b.category_id = c.id " +
            "WHERE c.name = #{categoryName} " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    @Results({
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "bookName", column = "book_name"),
            @Result(property = "bookAuthor", column = "book_author"),
            @Result(property = "bookVersion", column = "book_version"),
            @Result(property = "bookCoverUrl", column = "book_cover_url"),
            @Result(property = "categoryName", column = "categoryName")
    })
    List<BookDetail> getBooksByCategoryName(@Param("categoryName") String categoryName,
                                            @Param("pageSize") int pageSize,
                                            @Param("offset") int offset);

}