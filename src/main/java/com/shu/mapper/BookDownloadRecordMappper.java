package com.shu.mapper;

import com.shu.entity.vo.request.user.BookDownloadRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookDownloadRecordMappper {
    @Insert("insert into userbookdownloads(user_id,username, user_email, book_name, book_id, book_author,book_cover_url,book_download_points,create_time, update_time) " +
            "VALUES (#{userId},#{username},#{userEmail},#{bookName},#{bookId},#{bookAuthor},#{bookCoverUrl},#{bookDownloadPoints},#{createTime},#{updateTime})")
    void insertDownloadRecord(BookDownloadRecord bookDownloadRecord);

    @Select("select * from userbookdownloads where user_email = #{userEmail}")
    BookDownloadRecord selectDownloadRecord(String userEmail);

    @Select("select * from userbookdownloads where user_email = #{userEmail}")
    List<BookDownloadRecord> selectBookDownloadRecord(String userEmail);

    @Delete("delete  from userbookdownloads where download_id = #{downloadId}")
    void deleDownloadRecord(Integer downloadId);
}
