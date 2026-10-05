package com.hikaricommerce.mall.product.service;

import com.hikaricommerce.mall.product.dto.CategorySaveRequest;
import com.hikaricommerce.mall.product.entity.Category;
import com.hikaricommerce.mall.product.vo.CategoryTreeVO;

import java.util.List;

public interface CategoryService {
  public List<Category> listCategory();
  public List<CategoryTreeVO> listCategoryTree();
  public Category getCategoryById(Long id);
  public void createCategory(CategorySaveRequest request);
  public void updateCategory(Long id,CategorySaveRequest request);
  public void deleteBrand(Long id);
}
