package com.hikaricommerce.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hikaricommerce.mall.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("pms_spu")
public class Spu extends BaseEntity {
  private Long id;
  private Long brandId;
  // 其他商品字段
}
