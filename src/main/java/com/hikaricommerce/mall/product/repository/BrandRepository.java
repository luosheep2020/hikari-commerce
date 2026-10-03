package com.hikaricommerce.mall.product.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.repository.IRepository;
import com.hikaricommerce.mall.product.dto.BrandQuery;
import com.hikaricommerce.mall.product.entity.Brand;

public interface BrandRepository extends IRepository<Brand> {
  IPage<Brand> pageBrands(BrandQuery query);
}
