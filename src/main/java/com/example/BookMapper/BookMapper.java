package com.example.BookMapper;

import com.example.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    @Insert("INSERT INTO test.book (book_name, book_author, book_version, book_publish_house, book_grade, book_download_num, book_click_num, book_uploader, book_points, book_profile, book_cover_url, book_cover_uuid, book_file_uuid, category_name, create_time, update_time,category_alias) " +
            "VALUES (#{bookName}, #{bookAuthor}, #{bookVersion}, #{bookPulishHouse}, #{bookGrade}, #{bookDownloadNum}, #{bookClickNum}, #{bookUploader}, #{bookPoints}, #{bookProfile}, #{bookCoverUrl}, #{bookCoverUuid}, #{bookFileUuid},#{categoryName}, (SELECT book_categories.category_alias FROM test.book_categories WHERE category_name = #{categoryName}), NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "bookId")
    void insertBook(Book book);

    @Update("UPDATE test.book SET book_name = #{bookName}, book_version = #{bookVersion}, book_author = #{bookAuthor}, book_grade = #{bookGrade}, book_download_num = #{bookDownloadNum}, book_click_num = #{bookClickNum}, book_uploader = #{bookUploader}, book_points= #{bookPoints}, book_profile = #{bookProfile}, book_cover_url = #{bookCoverUrl},  category_name = #{categoryName} WHERE book_id = #{bookId}")
    int updateBook(Book book);

    @Delete({
            "<script>",
            "DELETE FROM book WHERE book_id IN ",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    void deleteBooksByIds(@Param("ids") List<Integer> ids);
    @Select("SELECT * FROM test.book")
    List<Book> selectAllBooks();
    @Select("SELECT COUNT(*) > 0 FROM test.db_account WHERE email = #{adminAccount} AND role = 'admin'")
    boolean isAdmin(String adminAccount);
    @Select("SELECT COUNT(*) > 0 FROM test.db_account WHERE email = #{userEmail} AND role = 'user'")
    boolean isUser(String userEmail);
    @Select("select * from test.book where book_id = #{bookId}")
    public Book selectBookById(int bookId);

    @Update("UPDATE book SET book_cover_url = #{bookCoverUrl}, book_cover_uuid = #{bookCoverUuid} WHERE book_id = #{bookId}")
    void updateBookcover(int bookId,String bookCoverUrl, String bookCoverUuid);

    @Update("UPDATE book SET book_file_uuid = #{bookFileUUID} WHERE book_id = #{bookId}")
    void updateBookFile(int bookId, String bookFileUUID);
}