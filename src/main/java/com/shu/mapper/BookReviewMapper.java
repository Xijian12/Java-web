package com.shu.mapper;

import com.shu.entity.vo.request.BookReview;
import com.shu.entity.vo.request.user.BookReviewwithavtar;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookReviewMapper {

    @Insert("INSERT INTO test.commentforbook (book_version, user_comment, user_grade, user_nickname, user_email, book_id,create_time,update_time) " +
            "VALUES (#{bookVersion}, #{userComment}, #{userGrade}, #{userNickname}, #{userEmail}, #{bookId},#{createTime},#{updateTime})")
    int insertBookReview(BookReview bookReview);

    @Select("SELECT * FROM test.commentforbook WHERE id = #{id}")
    BookReview selectBookReviewById(int id);

    @Update("UPDATE test.commentforbook SET book_version = #{bookVersion}, user_comment = #{userComment}, user_grade = #{userGrade}, user_nickname = #{userNickname}, user_email = #{userEmail}, book_id = #{bookId} WHERE id = #{id}")
    int updateBookReview(BookReview bookReview);

    void deleteCommentsByIds(List<Integer> ids);
    @Select("SELECT * FROM test.commentforbook")
    List<BookReview> selectAllBookReviews();
    @Select("SELECT COUNT(*) > 0 FROM test.db_account WHERE email = #{adminAccount} AND role = 'admin'")
    boolean isAdmin(String adminAccount);
    @Select("SELECT c.*, a.avatar_url FROM test.commentforbook c " +
            "JOIN test.db_account a ON c.user_email = a.email " +
            "WHERE c.book_id = #{bookId} " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    List<BookReviewwithavtar> findCommentsByBookId(@Param("bookId") int bookId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM test.commentforbook WHERE book_id = #{bookId}")
    int countCommentsByBookId(@Param("bookId") int bookId);

    @Update("update commentforbook set user_nickname = #{newUsername} where user_nickname =#{oldUsername}")
    void updateUsername(String oldUsername, String newUsername);
}