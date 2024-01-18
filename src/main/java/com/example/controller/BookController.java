package com.example.controller;

import com.example.entity.Result;
import com.example.entity.dto.Account;
import com.example.entity.vo.request.*;
import com.example.entity.vo.request.user.*;
import com.example.service.AccountService;
import com.example.service.BookCollectRecordService;
import com.example.service.BookDownloadRecordService;
import com.example.service.impl.BookService;
import com.example.utils.AliOSSUtils;
import com.example.utils.Constants;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;



@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private final BookService bookService;
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Resource
    AccountService accountService;
    @Autowired
    BookDownloadRecordService bookDownloadRecordService;
    @Autowired
    BookCollectRecordService bookCollectRecordService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return  ResponseEntity.ok(new Response(200, "操作成功"));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        bookService.addBookClickNum(book.getBookClickNum() + 1,bookId);
        return ResponseEntity.ok(new Response(200, "操作成功", book));
    }
    @GetMapping("/userUpload/{userEmail}")
    public ResponseEntity<?> getBookByEmail(@PathVariable String userEmail){
        List<Book> book = bookService.getBookByEmail(userEmail);
        return ResponseEntity.ok(new Response(200, "操作成功", book));
    }
    @GetMapping("/find")
        public ResponseEntity<?> getBooks(
                @RequestParam(required = false) String bookName,
                @RequestParam(required = false) String bookAuthor,
                @RequestParam(required = false) Integer bookPointsFloor,
                @RequestParam(required = false) Integer bookPointsUpper,
                @RequestParam(required = false)  Double bookGradeFloor,
                @RequestParam(required = false) Double bookGradeUpper,
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "10") int pageSize) {
            BookTop bookstop=new BookTop();
             bookstop = bookService.findBooks(bookName, bookAuthor, bookPointsFloor, bookPointsUpper, bookGradeFloor, bookGradeUpper, page, pageSize);

            return ResponseEntity.ok(bookstop);
        }
   @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(new Response(200, "操作成功", books));
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {

        bookService.updateBook(book);
        return ResponseEntity.ok(new Response(200, "操作成功", null));
    }
    @PutMapping("/admin")
    public ResponseEntity<?> updateBookAdmin(@RequestBody Book book) {

        bookService.updateBook(book);
        return ResponseEntity.ok(new Response(200, "操作成功", null));
    }
    @PostMapping("/download")
    public ResponseEntity<?> downloadBook(@RequestBody BookRequest book) throws IOException {
        DownloadBook test = new DownloadBook();
        test = bookService.downloadBook(book.getUserEmail(), book.getBookId());
        Book bookobj = bookService.getBookById(book.getBookId());
        int lastDotIndex = bookobj.getBookFileUuid().lastIndexOf(".");
        // 使用 substring 提取扩展名部分
        String fileExtension = null;
        if (lastDotIndex != -1 && lastDotIndex < bookobj.getBookFileUuid().length() - 1) {
            fileExtension = bookobj.getBookFileUuid().substring(lastDotIndex + 1);
        }
        String newFileName = bookobj.getBookName() + '.' + fileExtension;
        if (test.getTotal() >= 1) {
            //如果下载成功，则给上传者积分奖励
            Account account = accountService.findAccountByNameOrEmail(bookobj.getBookUploader());
            if(account != null){
                String newUserInfo = accountService.updateUserInfo(account.getUsername(),null,null,account.getPoints()+(int)(Constants.pointRate * bookobj.getBookPoints()));
            }
            //将下载记录加入到表中
            bookDownloadRecordService.addBookDownloadRecord(book);
            bookService.addBookDownloadNum(bookobj.getBookDownloadNum() + 1,book.getBookId());
            return ResponseEntity.ok(new Response(200, "操作成功", aliOSSUtils.GetFileDownloadUrl(test.getUrl(), newFileName)));
        } else {
            return ResponseEntity.ok(new Response(200, "操作失败，积分不够", null));
        }
    }
    @DeleteMapping("/admin")
    public ResponseEntity<?> deleteBooks(@RequestBody BookDeletionRequest request) throws Exception {
        if (bookService.deleteBooksIfAdmin(request)) {
            List<Book> books = bookService.selectBooksByIds(request.getBookIds());
            if(books!=null){
                for (Book book : books) {
                    //如果图书封面不是默认封面，则可以删除该封面
                    if (!book.getBookCoverUrl().equals(Constants.defaultBookCoverUrl)) {
                        aliOSSUtils.DeleteFile(book.getBookCoverUuid());
                    }
                    aliOSSUtils.DeleteFile(book.getBookFileUuid());
                }
            }
            return ResponseEntity.ok(new Response(200, "操作成功", null));
        } else {
            return ResponseEntity.ok(new Response(0, "操作失败，该账户不是管理员！", null));
        }
    }
    @GetMapping("/highest/{n}")
    public List<Book> getTopNBooks(@PathVariable int n) {
        return bookService.findTopNBooks(n);
    }
    @GetMapping("/test")
    public ResponseEntity<?> selectBooksById(@RequestBody BookDeletionRequest request) throws Exception {   List<Book> books=bookService.selectBooksByIds(request.getBookIds());
        if(books!=null){
            for (Book book : books) {
                aliOSSUtils.DeleteFile(book.getBookFileUuid());
            }
        }
        return ResponseEntity.ok(new Response(200, "操作成功",books ));

    }
  @PutMapping ("/downClicks/{bookVersion}")
  public ResponseEntity<?> updateByVersion(@RequestParam(required = false) String bookVersion,
                              @RequestParam(required = false) int bookDownloadNum,
                              @RequestParam(required = false) int bookClickNum)
  {      bookService.updateClickByVersion(bookVersion,bookDownloadNum,bookClickNum);
      return ResponseEntity.ok(new Response(200, "操作成功", null));
  }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteBooksUser(@RequestBody BookDeletionRequest request) throws Exception {

        if (bookService.deleteBooksIfUser(request)) {
            List<Book> books = bookService.selectBooksByIds(request.getBookIds());
            if(books!=null){
                for (Book book : books) {
                    //如果图书封面不是默认封面，则可以删除该封面
                    if (!book.getBookCoverUrl().equals(Constants.defaultBookCoverUrl)) {
                        aliOSSUtils.DeleteFile(book.getBookCoverUuid());
                    }
                    aliOSSUtils.DeleteFile(book.getBookFileUuid());
                }
            }

            return ResponseEntity.ok(new Response(200, "操作成功", null));
        } else {
            return ResponseEntity.ok(new Response(0, "操作失败，不能删除别人的图书！", null));
        }
    }

    //获取用户下载记录
    @GetMapping("/downloadRecord")
    public Result getDownloadRecordById(String userEmail,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        MaterialPage materialPage = bookDownloadRecordService.queryBookDownloadRecord(userEmail,page,pageSize);
        return Result.success(materialPage);
    }
    //删除用户下载记录
    @DeleteMapping ("/downloadRecord")
    public Result deleteDownloadRecordById(Integer downloadId){
        bookDownloadRecordService.deleteBookDownloadRecord(downloadId);
        return Result.success();
    }

    //添加用户收藏记录
    @PostMapping("/collectRecord")
    public Result addCollectRecordById(@RequestBody BookCollectRecord bookCollectRecord){
        String isError = bookCollectRecordService.addBookCollectRecord(bookCollectRecord);
        if(isError == null){
            return Result.success();
        }
        return Result.error(isError);
    }
    //获取用户收藏记录
    @GetMapping("/collectRecord")
    public Result getCollectRecordById(String userEmail,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        MaterialPage materialPage = bookCollectRecordService.queryBookCollectRecord(userEmail,page,pageSize);
        if(materialPage == null){
            return Result.error("用户不存在");
        }
        return Result.success(materialPage);
    }
    //删除用户收藏记录
    @DeleteMapping ("/collectRecord")
    public Result deleteCollectRecordById(Integer collectId){
        bookCollectRecordService.deleteBookCollectRecord(collectId);
        return Result.success();
    }

    //获取所有图书的点击量总和
    @GetMapping("clickNum")
    public Result getBookTotalClickNum(){
        return Result.success(bookService.getBookTotalClickNum());
    }

    //获取所有图书的下载量总和
    @GetMapping("downloadNum")
    public Result getBookTotalDownloadNum(){
        return Result.success(bookService.getBookTotalDownloadNum());
    }

}