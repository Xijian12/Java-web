package com.shu.mapper;

import com.shu.entity.vo.request.user.BookCollectRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookCollectRecordMappper {
    @Insert("insert into userbookcollect(user_id,username, user_email, book_name, book_id ,book_cover_url, book_author, book_download_points, create_time, update_time) " +
            "VALUES (#{userId},#{username},#{userEmail},#{bookName},#{bookId},#{bookCoverUrl},#{bookAuthor},#{bookDownloadPoints},#{createTime},#{updateTime})")
    void insertBookCollectRecord(BookCollectRecord bookCollectRecord);

    @Select("select * from userbookcollect where user_email = #{userEmail}")
    BookCollectRecord selectBookCollectRecord(String userEmail);

    @Select("select * from userbookcollect where user_email = #{userEmail}")
    List<BookCollectRecord> queryBookCollectRecord(String userEmail);

    @Delete("delete  from userbookcollect where collect_id = #{collectId}")
    void deleteBookCollectRecord(Integer collectId);

    @Select("select * from userbookcollect where user_email = #{userEmail} and book_id =#{bookId}")
    BookCollectRecord isRepeatCollectRecord(String userEmail,Integer bookId);
}
