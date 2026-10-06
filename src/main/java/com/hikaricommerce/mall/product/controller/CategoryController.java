package com.hikaricommerce.mall.product.controller;

import com.hikaricommerce.mall.common.result.ApiResponse;
import com.hikaricommerce.mall.product.dto.CategorySaveRequest;
import com.hikaricommerce.mall.product.entity.Category;
import com.hikaricommerce.mall.product.service.CategoryService;
import com.hikaricommerce.mall.product.vo.CategoryTreeVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

  @Resource
  private CategoryService categoryService;

  @GetMapping
  public ApiResponse<List<Category>> listCategory() {
    return ApiResponse.success(categoryService.listCategory());
  }

  @GetMapping("/tree")
  public ApiResponse<List<CategoryTreeVO>> listCategoryTree() {
    return ApiResponse.success(categoryService.listCategoryTree());
  }

  @GetMapping("/{id}")
  public ApiResponse<Category> getCategoryById(@PathVariable Long id) {
    return ApiResponse.success(categoryService.getCategoryById(id));
  }

  @PostMapping
  public ApiResponse<Void> createCategory(
    @Valid @RequestBody CategorySaveRequest request
  ) {
    categoryService.createCategory(request);
    return ApiResponse.success();
  }

  @PutMapping("/{id}")
  public ApiResponse<Void> updateCategory(
    @PathVariable Long id,
    @Valid @RequestBody CategorySaveRequest request
  ) {
    categoryService.updateCategory(id, request);
    return ApiResponse.success();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ApiResponse.success();
  }
}
