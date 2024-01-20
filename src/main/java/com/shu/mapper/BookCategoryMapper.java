package com.shu.mapper;

import com.shu.entity.vo.request.Book;
import com.shu.entity.vo.request.BookCategory;
import com.shu.entity.vo.request.BookDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookCategoryMapper {

    @Select("SELECT * FROM test.book_categories WHERE book_categories.category_id= #{categoryId}")
    BookCategory selectCategoryById(int categoryId);

    @Select("SELECT * FROM test.book_categories LIMIT #{pageSize} OFFSET #{offset}")
    List<BookCategory> selectAllCategories(@Param("offset")int offset,@Param("pageSize")int pageSize);


    @Update("UPDATE test.book_categories SET category_name = #{categoryName},category_alias =#{categoryAlias}, category_click_num = #{categoryClickNum}, category_download_num = #{categoryDownloadNum} WHERE category_id = #{categoryId}")
    void updateCategory(BookCategory category);

    @Delete("DELETE FROM  test.book_categories WHERE book_categories.category_id = #{categoryId}")
    void deleteCategory(int id);
    @Insert("INSERT INTO test.book_categories (category_name, category_alias, category_click_num, category_download_num) VALUES (#{categoryName}, #{categoryAlias}, #{categoryClickNum}, #{categoryDownloadNum})")
    @Options(useGeneratedKeys = true, keyProperty = "categoryId")
    void insertCategory(BookCategory category);
    @Select("SELECT * FROM test.book b WHERE b.category_name IN (SELECT c.category_name FROM book_categories c WHERE c.category_name = #{categoryName})")
    List<BookDetail> getBooksByCategoryId(@Param("categoryId") long categoryId);

    // 其他与图书分类相关的数据库操作...
    @Select("SELECT *  " +
            "FROM test.book b " +
            "JOIN test.book_categories c ON b.category_name = c.category_name " +
            "WHERE c.category_name = #{categoryName} " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    @Results({
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "bookName", column = "book_name"),
            @Result(property = "bookAuthor", column = "book_author"),
            @Result(property = "bookVersion", column = "book_version"),
            @Result(property = "bookCoverUrl", column = "book_cover_url"),
            @Result(property = "categoryName", column = "categoryName")
    })
    List<Book> getBooksByCategoryName(@Param("categoryName") String categoryName,
                                      @Param("pageSize") int pageSize,
                                      @Param("offset") int offset);




    @Delete({
            "<script>",
            "DELETE FROM test.book_categories WHERE category_id IN ",
            "<foreach item='id' collection='categoryIds' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    void deleteCategoriesByIds(@Param("categoryIds") List<Integer> categoryIds);



}