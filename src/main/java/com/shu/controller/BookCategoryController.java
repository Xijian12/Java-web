package com.shu.controller;

import com.shu.entity.vo.request.BookCategory;
import com.shu.entity.vo.request.BookResponse;
import com.shu.entity.vo.request.CategoryRequest;
import com.shu.entity.vo.request.Response;
import com.shu.service.impl.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")


public class BookCategoryController {
    @Autowired
    private BookCategoryService bookCategoryService;
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest request) {
        bookCategoryService.addCategory(request.getCategoryName(), request.getCategoryAlias());
        return ResponseEntity.ok(new Response(200, "操作成功"));
    }
    @GetMapping("/get/{categoryId}")
    public BookCategory getCategoryById(@PathVariable int categoryId) {
        return bookCategoryService.getCategoryById(categoryId);
    }
//获取所有分类，带分页
    @GetMapping
    public List<BookCategory> getAllCategories(@RequestParam("page") int page,
                                               @RequestParam ("pageSize")int pageSize) {

        return bookCategoryService.getAllCategories(page,pageSize);
    }

    @PutMapping
    public  ResponseEntity<?>updateCategory(@RequestBody BookCategory category) {
        bookCategoryService.updateCategory(category);
         return ResponseEntity.ok(new Response(200, "操作成功"));
    }

    @DeleteMapping("/{categoryIds}")
    public ResponseEntity<?> deleteCategories(@PathVariable List<Integer> categoryIds) {
        bookCategoryService.deleteCategories(categoryIds);
        ;return ResponseEntity.ok(new Response(200, "操作成功"));
    }
    //根据分类返回图书
    @GetMapping("/detail")
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


}

