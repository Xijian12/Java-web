package com.shu.mapper;

import com.shu.entity.vo.request.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
@Update(("UPDATE book SET book_grade = ( SELECT AVG(user_grade) FROM commentforbook WHERE commentforbook.book_id = book.book_id)"))
   void updateBookGrade();

    @Insert("INSERT INTO test.book (book_name, book_author, book_version, book_publish_house, book_grade, book_download_num, book_click_num, book_uploader, book_points, book_profile, book_cover_url, book_cover_uuid, book_file_uuid, category_name,category_alias, create_time, update_time) " +
            "VALUES (#{bookName}, #{bookAuthor}, #{bookVersion}, #{bookPublishHouse}, #{bookGrade}, #{bookDownloadNum}, #{bookClickNum}, #{bookUploader}, #{bookPoints}, #{bookProfile}, #{bookCoverUrl}, #{bookCoverUuid}, #{bookFileUuid},#{categoryName}, (SELECT book_categories.category_alias FROM test.book_categories WHERE category_name = #{categoryName}), NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "bookId")
    void insertBook(Book book);
 @Update("UPDATE test.book set book_download_num = #{bookDownloadNum}, book_click_num = #{bookClickNum} where test.book.book_version=#{bookVersion}")
    void updateClickByversion(String bookVersion,int bookDownloadNum,int bookClickNum);
    @Update("UPDATE test.book SET book_name = #{bookName}, book.book_publish_house=#{bookPublishHouse},book_version = #{bookVersion}, book_author = #{bookAuthor}, book_grade = #{bookGrade}, book_download_num = #{bookDownloadNum}, book_click_num = #{bookClickNum}, book_uploader = #{bookUploader}, book_points= #{bookPoints}, book_profile = #{bookProfile}, book_cover_url = #{bookCoverUrl},  category_name = #{categoryName} ,update_time= NOW() WHERE book_id = #{bookId}")
    int updateBook(Book book);
    @Select("SELECT * FROM  test.book ORDER BY book_grade DESC LIMIT #{n}")
    List<Book> findTopNBooks(int n);
    @Delete({
            "<script>",

            // 删除图书
            "DELETE FROM book WHERE book_id IN ",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>;",




            "</script>"
    })
     void deleteBooksByIds(@Param("ids") List<Integer> ids);

    @Delete("delete  from test.commentforbook where book_id = #{bookId}")
    void deleteBookCommentByIds(int bookId);

//    @Select({
//            "<script>",
//            "SELECT * FROM test.book",
//            "<where>",
//            "<if test='bookName != null'>AND book_name LIKE CONCAT('%', #{bookName}, '%')</if>",
//            "<if test='bookAuthor != null'>OR book_author LIKE CONCAT('%', #{bookAuthor}, '%')</if>",
//            "<if test='bookPointsFloor != null'>OR book_points>=#{bookPointsFloor}</if>",
//            "<if test='bookPointsUpper != null'>OR #{bookPointsUpper}>=book_points</if>",
//            "<if test='bookGradeUpper != null'>AND #{bookGradeUpper}>=book_grade</if>",
//            "<if test='bookGradeFloor != null'>AND book_grade >= #{bookGradeFloor}</if>",
//
//           "</where>",
//            "LIMIT #{offset}, #{limit}",
//            "</script>"
//    })

    List<Book> findBooks(String bookName);
    @Select("SELECT COUNT(*)FROM test.book where book_uploader=#{userEmail}")
    int countBooksById(String userEmail);
//   @Select({
//           "<script>",
//           "SELECT COUNT(*) FROM test.book",
//           "<where>",
//           "<if test='bookName != null'>AND book_name LIKE CONCAT('%', #{bookName}, '%')</if>",
//           "<if test='bookAuthor != null'>AND book_author LIKE CONCAT('%', #{bookAuthor}, '%')</if>",
//           "<if test='bookPointsFloor != null'>AND book_points>=#{bookPointsFloor}</if>",
//           "<if test='bookPointsUpper != null'>AND #{bookPointsUpper}>=book_points</if>",
//           "<if test='bookGradeUpper != null'>AND #{bookGradeUpper}>=book_grade</if>",
//           "<if test='bookGradeFloor != null'>AND book_grade >= #{bookGradeFloor}</if>",
//           "</where>",
//           "</script>"
//   })
   int countBooks(@Param("bookName") String bookName,
                  @Param("bookAuthor") String bookAuthor,
                  @Param("bookPointsFloor") Integer bookPointsFloor,
                  @Param("bookPointsUpper") Integer bookPointsUpper,
                  @Param("bookGradeFloor") Double bookGradeFloor,
                  @Param("bookGradeUpper") Double bookGradeUpper);
    @Select({
            "<script>",
            "SELECT * FROM test.book WHERE book_id IN ",
            "<foreach item='id' collection='bookIds' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Book> selectBooksByIds(@Param("bookIds") List<Integer> bookIds);


    @Select("SELECT * FROM test.book")
    List<Book> selectAllBooks();
    @Select("SELECT COUNT(*) > 0 FROM test.db_account WHERE email = #{adminAccount} AND role = 'admin'")
    boolean isAdmin(String adminAccount);
    @Select("SELECT COUNT(*) > 0 FROM test.db_account WHERE email = #{userEmail} AND role = 'user'")
    boolean isUser(String userEmail);
    @Select("select * from test.book where book_id = #{bookId}")
    public Book selectBookById(int bookId);
    @Select("select * from test.book where book_version = #{bookVerison}")
    public Book selectBookByVersion(String bookVerison);
    @Select("SELECT book_file_uuid FROM test.book, test.db_account " +
            "WHERE db_account.points >= book.book_points " +
            "AND book_id = #{bookId} " +
            "AND email = #{userEmail}")
    public String downloadBook(String userEmail, int bookId);
    @Update("UPDATE test.db_account SET points = points - (SELECT book_points FROM test.book WHERE book_id = #{bookId}) " +
            "WHERE email = #{userEmail} " +
            "AND points >= (SELECT book_points FROM test.book WHERE book_id = #{bookId})")
    public int updateUserPoints(String userEmail, int bookId);

    @Update("UPDATE book SET book_cover_url = #{bookCoverUrl}, book_cover_uuid = #{bookCoverUuid} WHERE book_id = #{bookId}")
    void updateBookcover(int bookId,String bookCoverUrl, String bookCoverUuid);

    @Update("UPDATE book SET book_file_uuid = #{bookFileUUID} WHERE book_id = #{bookId}")
    void updateBookFile(int bookId, String bookFileUUID);

    @Select("select * from book where book_uploader = #{userEmail} LIMIT #{size} OFFSET #{offset}")
    List<Book> getBookObject(String userEmail,int offset,int size);

    @Select("select sum(book_click_num) from book")
    Long selectBookTotalClickNum();

    @Select("select sum(book_download_num) from book")
    Long selectBookTotalDownloadNum();

    //给对应图书增加点击点击量
    @Update("update book set book_click_num = #{newBookClickNum} where book_id = #{bookId}")
    void updateBookClickNum(Integer newBookClickNum,Integer bookId);

    //给对应图书增加下载量
    @Update("update book set book_download_num = #{newBookDownloadNum} where book_id = #{bookId}")
    void updateBookDownloadNum(Integer newBookDownloadNum, Integer bookId);

    //通过版号和上传者判断是否重复上传这本书
    @Select("select * from book where book_version = #{bookVersion} and book_uploader = #{bookUploader}")
    Book selectRepeatBook(String bookVersion,String bookUploader);
}