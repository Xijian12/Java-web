package com.example.BookMapper;

import com.example.pojo.BookReview;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookReviewMapper {

    @Insert("INSERT INTO book_reviews (book_edition, review, rating, user_nickname, user_account, id) " +
            "VALUES (#{bookEdition}, #{review}, #{rating}, #{userNickname}, #{userAccount}, #{bookId})")
    int insertBookReview(BookReview bookReview);

    @Select("SELECT * FROM book_reviews WHERE id = #{id}")
    BookReview selectBookReviewById(int id);

    @Update("UPDATE book_reviews SET book_edition = #{bookEdition}, review = #{review}, rating = #{rating}, user_nickname = #{userNickname}, user_account = #{userAccount}, book_id = #{bookId} WHERE id = #{id}")
    int updateBookReview(BookReview bookReview);

    @Delete("DELETE FROM book_reviews WHERE id = #{id}")
    int deleteBookReviewById(int id);

    @Select("SELECT * FROM book_reviews")
    List<BookReview> selectAllBookReviews();
}