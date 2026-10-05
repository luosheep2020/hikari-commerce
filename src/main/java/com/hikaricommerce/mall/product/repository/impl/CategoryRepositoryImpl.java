package com.hikaricommerce.mall.product.repository.impl;

import com.baomidou.mybatisplus.spring.repository.CrudRepository;
import com.hikaricommerce.mall.product.entity.Category;
import com.hikaricommerce.mall.product.mapper.CategoryMapper;
import com.hikaricommerce.mall.product.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl
  extends CrudRepository<CategoryMapper, Category>
  implements CategoryRepository {
}
