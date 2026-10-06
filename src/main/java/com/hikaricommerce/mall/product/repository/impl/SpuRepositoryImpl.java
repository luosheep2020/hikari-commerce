package com.hikaricommerce.mall.product.repository.impl;

import com.baomidou.mybatisplus.spring.repository.CrudRepository;
import com.hikaricommerce.mall.product.entity.Spu;
import com.hikaricommerce.mall.product.mapper.SpuMapper;
import com.hikaricommerce.mall.product.repository.SpuRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SpuRepositoryImpl
  extends CrudRepository<SpuMapper, Spu>
  implements SpuRepository {
}
