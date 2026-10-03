package com.hikaricommerce.mall.product.repository.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.repository.CrudRepository;
import com.hikaricommerce.mall.product.dto.BrandQuery;
import com.hikaricommerce.mall.product.entity.Brand;
import com.hikaricommerce.mall.product.mapper.BrandMapper;
import com.hikaricommerce.mall.product.repository.BrandRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class BrandRepositoryImpl
  extends CrudRepository<BrandMapper, Brand>
  implements BrandRepository {


  @Override
  public IPage<Brand> pageBrands(BrandQuery query) {
    Page<Brand> page = new Page<>(
      query.getPageNum(),
      query.getPageSize()
    );

    return lambdaQuery()
      .like(
        StringUtils.hasText(query.getName()),
        Brand::getName,
        query.getName()
      )
      .orderByAsc(Brand::getSort)
      .orderByAsc(Brand::getId)
      .page(page);
  }
}
