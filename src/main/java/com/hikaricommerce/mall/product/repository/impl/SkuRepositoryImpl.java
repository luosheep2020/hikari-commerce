package com.hikaricommerce.mall.product.repository.impl;

import com.baomidou.mybatisplus.spring.repository.CrudRepository;
import com.hikaricommerce.mall.product.entity.Sku;
import com.hikaricommerce.mall.product.mapper.SkuMapper;
import com.hikaricommerce.mall.product.repository.SkuRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SkuRepositoryImpl
  extends CrudRepository<SkuMapper, Sku>
  implements SkuRepository {
}
