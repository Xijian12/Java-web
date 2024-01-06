package com.example.BookController;

import com.example.BookService.BookCategoryService;
import com.example.pojo.*;
import com.example.BookService.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class BookCategoryController {
    @Autowired
    private BookCategoryService bookCategoryService;
    @PostMapping("/admin/category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest request) {
        bookCategoryService.addCategory(request.getCategoryName(), request.getCategoryAlias());
        return ResponseEntity.ok(new Response(0, "操作成功"));
    }
    @GetMapping("/get/{id}")
    public BookCategory getCategoryById(@PathVariable int id) {
        return bookCategoryService.getCategoryById(id);
    }

    @GetMapping("/category")
    public List<BookCategory> getAllCategories() {
        return bookCategoryService.getAllCategories();
    }



    // 内部类用于响应

    @PutMapping
    public void updateCategory(@RequestBody BookCategory category) {
        bookCategoryService.updateCategory(category);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable int id) {
        boolean isSuccess = bookCategoryService.deleteCategory(id);
        if (isSuccess) {
            return ResponseEntity.ok(new Response(0, "操作成功"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(1, "操作失败"));
        }
    }
    @GetMapping("/category/detail")
    public ResponseEntity<Response> getBooksByCategoryName(
            @RequestParam("categoryName") String categoryName,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        // 调用Service层获取分页后的图书列表
        BookResponse bookResponse = bookCategoryService.getBooksByCategoryName(categoryName, page, pageSize);

        // 构建响应对象
        Response response = new Response();
        response.setCode(0);
        response.setMessage("操作成功");
        response.setData(bookResponse);

        // 返回响应实体
        return ResponseEntity.ok(response);
    }

    // 其他请求映射...
}

