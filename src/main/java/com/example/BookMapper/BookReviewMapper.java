package com.example.BookMapper;

import com.example.pojo.BookReview;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookReviewMapper {

    @Insert("INSERT INTO test.book_comment (book_version, user_comment, user_grade, user_nickname, user_email, book_id) " +
            "VALUES (#{bookVersion}, #{userComment}, #{userGrade}, #{userNickname}, #{userEmail}, #{bookId})")
    int insertBookReview(BookReview bookReview);

    @Select("SELECT * FROM test.book_comment WHERE id = #{id}")
    BookReview selectBookReviewById(int id);

    @Update("UPDATE book_reviews SET book_edition = #{bookEdition}, review = #{review}, rating = #{rating}, user_nickname = #{userNickname}, user_account = #{userAccount}, book_id = #{bookId} WHERE id = #{id}")
    int updateBookReview(BookReview bookReview);

    @Delete({
            "<script>",
            "DELETE FROM test.book_comment WHERE id IN",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    void deleteCommentsByIds(@Param("ids") List<Integer> ids);
    @Select("SELECT * FROM book_reviews")
    List<BookReview> selectAllBookReviews();
    @Select("SELECT COUNT(*) > 0 FROM test.db_account WHERE email = #{adminAccount} AND role = 'admin'")
    boolean isAdmin(String adminAccount);
    @Select("SELECT * FROM test.book_comment WHERE book_id = #{bookId} LIMIT #{pageSize} OFFSET #{offset}")
    List<BookReview> findCommentsByBookId(@Param("bookId") int bookId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM test.book_comment WHERE book_id = #{bookId}")
    int countCommentsByBookId(@Param("bookId") int bookId);
}