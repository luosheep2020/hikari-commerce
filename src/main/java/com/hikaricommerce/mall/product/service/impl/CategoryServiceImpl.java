package com.hikaricommerce.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hikaricommerce.mall.common.enums.ErrorCode;
import com.hikaricommerce.mall.common.exception.BusinessException;
import com.hikaricommerce.mall.product.dto.CategorySaveRequest;
import com.hikaricommerce.mall.product.entity.Category;
import com.hikaricommerce.mall.product.entity.Spu;
import com.hikaricommerce.mall.product.repository.BrandRepository;
import com.hikaricommerce.mall.product.repository.CategoryRepository;
import com.hikaricommerce.mall.product.repository.SpuRepository;
import com.hikaricommerce.mall.product.service.CategoryService;
import com.hikaricommerce.mall.product.vo.CategoryTreeVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Resource
  private CategoryRepository categoryRepository;
  @Resource
  private SpuRepository spuRepository;
  @Resource
  private BrandRepository brandRepository;
  
  @Override
  public List<Category> listCategory() {
    return categoryRepository.list();
  }

  @Override
  public List<CategoryTreeVO> listCategoryTree() {
//    Select all category
    List<Category> categories = categoryRepository.list(
      new LambdaQueryWrapper<Category>()
        .orderByAsc(Category::getSort)
        .orderByAsc(Category::getId)
    );

//    create node,node->map
    Map<Long, CategoryTreeVO> nodeMap = new HashMap<>();
    for (Category category : categories) {
      CategoryTreeVO node = new CategoryTreeVO();
      BeanUtils.copyProperties(category, node);
      nodeMap.put(node.getId(), node);
    }

//    build category tree
    List<CategoryTreeVO> root = new ArrayList<>();
    for (Category category : categories) {
      CategoryTreeVO node = nodeMap.get(category.getId());
      Long parentId = node.getParentId();
      if (Long.valueOf(0L).equals(parentId)) {
        root.add(node);
      } else {
        CategoryTreeVO parent = nodeMap.get(parentId);
        if (parent == null) {
          throw new IllegalStateException(
            "Parent category not found: " + parentId
          );
        }
        parent.getChildren().add(node);
      }
    }
    return root;
  }

  @Override
  public Category getCategoryById(Long id) {
    Category category=categoryRepository.getById(id);
    if (category==null){
      throw new BusinessException(ErrorCode.CATEGORY_NOT_FOUND);
    }
    return category;
  }

  @Override
  public void createCategory(CategorySaveRequest request) {
    Long parentId = request.getParentId();
    String name = request.getName().trim();

    // 1. 非顶级分类，检查父分类是否存在
    if (parentId != 0L &&
      categoryRepository.getById(parentId) == null) {
      throw new BusinessException(
        ErrorCode.CATEGORY_PARENT_NOT_FOUND
      );
    }

    // 2. 检查同一个父分类下是否重名
    boolean exists = categoryRepository.exists(
      new LambdaQueryWrapper<Category>()
        .eq(Category::getParentId, parentId)
        .eq(Category::getName, name)
    );

    if (exists) {
      throw new BusinessException(
        ErrorCode.CATEGORY_NAME_ALREADY_EXISTS
      );
    }

    // 3. 保存分类
    Category category = new Category();
    BeanUtils.copyProperties(request, category);
    category.setName(name);

    categoryRepository.save(category);
  }

  @Override
  public void updateCategory(Long id, CategorySaveRequest request) {
    Category category=categoryRepository.getById(id);
    if (category==null){
      throw new BusinessException(ErrorCode.CATEGORY_NOT_FOUND);
    }

    Long parentId= request.getParentId();
    String name=request.getName().trim();

    validateParent(id,parentId);
    boolean exists=categoryRepository.exists(
      new LambdaQueryWrapper<Category>()
        .eq(Category::getParentId,parentId)
        .eq(Category::getName,name)
        .ne(Category::getId,id)
    );
    if (exists) {
      throw new BusinessException(
        ErrorCode.CATEGORY_NAME_ALREADY_EXISTS
      );
    }
    BeanUtils.copyProperties(request, category);
    category.setName(name);
    categoryRepository.updateById(category);
  }

  @Override
  public void deleteCategory(Long id) {
    Category category=categoryRepository.getById(id);
    if (category==null){
      throw new BusinessException(ErrorCode.CATEGORY_NOT_FOUND);
    }
    boolean hasChildren=categoryRepository.exists(
      new LambdaQueryWrapper<Category>()
        .eq(Category::getParentId,id)
    );
    if (hasChildren){
      throw new BusinessException(ErrorCode.CATEGORY_HAS_CHILDREN);
    }
    boolean hasProducts=spuRepository.exists(
      new LambdaQueryWrapper<Spu>()
        .eq(Spu::getCategoryId, id)
    );
    if (hasProducts){
      throw new BusinessException(ErrorCode.CATEGORY_HAS_PRODUCTS);
    }
    brandRepository.removeById(id);
  }

  private void validateParent(Long categoryId, Long parentId) {
    if (Long.valueOf(0L).equals(parentId)) {
      return;
    }

    if (categoryId.equals(parentId)) {
      throw new BusinessException(
        ErrorCode.CATEGORY_PARENT_IS_SELF
      );
    }

    Set<Long> visited = new HashSet<>();
    Long currentId = parentId;

    while (!Long.valueOf(0L).equals(currentId)) {
      if (currentId == null) {
        throw new BusinessException(
          ErrorCode.CATEGORY_HIERARCHY_INVALID
        );
      }

      // 向上找到了当前分类，说明新父分类是自己的子孙
      if (categoryId.equals(currentId)) {
        throw new BusinessException(
          ErrorCode.CATEGORY_PARENT_IS_DESCENDANT
        );
      }

      // 同一个节点出现两次，说明已有数据存在循环
      if (!visited.add(currentId)) {
        throw new BusinessException(
          ErrorCode.CATEGORY_HIERARCHY_INVALID
        );
      }
      Category parent = categoryRepository.getById(currentId);

      if (parent == null) {
        throw new BusinessException(
          currentId.equals(parentId)
            ? ErrorCode.CATEGORY_PARENT_NOT_FOUND
            : ErrorCode.CATEGORY_HIERARCHY_INVALID
        );
      }

      currentId = parent.getParentId();
    }
  }
}
