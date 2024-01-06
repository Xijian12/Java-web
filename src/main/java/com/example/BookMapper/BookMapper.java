package com.example.BookMapper;

import com.example.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    @Insert({
            "<script>",
            "INSERT INTO books (",
            "book_name, book_version, book_author, book_grade, ",
            "<if test='downloads != null'>book_download_url,</if>",
            "<if test='clicks != null'>book_click_url,</if>",
            "uploader, ",
            "<if test='pointsRequired != null'>points_required,</if>",
            "description, book_cover_url",
            "<if test='categoryId != null'>, category_id</if>",
            ") VALUES (",
            "#{bookName}, #{bookVersion}, #{bookAuthor}, #{book_grade}, ",
            "<if test='downloads != null'>#{downloads},</if>",
            "<if test='clicks != null'>#{clicks},</if>",
            "#{uploader}, ",
            "<if test='pointsRequired != null'>#{pointsRequired},</if>",
            "#{description}, #{bookImg}, #{reviews}",
            "<if test='categoryId != null'>, #{categoryId}</if>",
            ")",
            "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertBook(Book book);

    @Select("SELECT * FROM books WHERE book_id = #{id}")
    Book selectBookById(int id);

    @Update("UPDATE books SET book_name = #{bookName}, book_version = #{bookVersion}, book_author = #{bookAuthor}, book_grade = #{book_grade}, book_download_num = #{bookDownloadNum}, book_click_num = #{bookClickNum}, uploader = #{uploader}, book_points= #{bookPoints}, book_porfile = #{bookPorfile}, book_cover_url = #{bookCoverUrl},  category_id = #{categoryId} WHERE book_id = #{bookId}")
    int updateBook(Book book);

    @Delete({
            "<script>",
            "DELETE FROM books WHERE book_id IN ",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    void deleteBooksByIds(@Param("ids") List<Integer> ids);
    @Select("SELECT * FROM test.book")
    List<Book> selectAllBooks();
}