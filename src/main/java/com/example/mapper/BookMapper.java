package com.example.mapper;

import com.example.entity.dto.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookMapper {
    @Select("select * from book where book_id = #{bookId}")
    public Book selectBookById(int bookId);

    @Update("UPDATE book SET book_cover_url = #{bookCoverUrl}, book_cover_uuid = #{bookCoverUuid} WHERE book_id = #{bookId}")
    void updateBookcover(int bookId,String bookCoverUrl, String bookCoverUuid);
}
