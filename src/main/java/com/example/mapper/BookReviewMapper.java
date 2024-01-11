package com.example.mapper;

import com.example.entity.vo.request.BookReview;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookReviewMapper {

    @Insert("INSERT INTO test.commentforbook (book_version, user_comment, user_grade, user_nickname, user_email, book_id) " +
            "VALUES (#{bookVersion}, #{userComment}, #{userGrade}, #{userNickname}, #{userEmail}, #{bookId})")
    int insertBookReview(BookReview bookReview);

    @Select("SELECT * FROM test.commentforbook WHERE id = #{id}")
    BookReview selectBookReviewById(int id);

    @Update("UPDATE test.commentforbook SET book_version = #{bookVersion}, user_comment = #{userComment}, user_grade = #{userGrade}, user_nickname = #{userNickname}, user_email = #{userEmail}, book_id = #{bookId} WHERE id = #{id}")
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
    @Select("SELECT * FROM test.commentforbook")
    List<BookReview> selectAllBookReviews();
    @Select("SELECT COUNT(*) > 0 FROM test.db_account WHERE email = #{adminAccount} AND role = 'admin'")
    boolean isAdmin(String adminAccount);
    @Select("SELECT * FROM test.commentforbook WHERE book_id = #{bookId} LIMIT #{pageSize} OFFSET #{offset}")
    List<BookReview> findCommentsByBookId(@Param("bookId") int bookId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM test.commentforbook WHERE book_id = #{bookId}")
    int countCommentsByBookId(@Param("bookId") int bookId);
}