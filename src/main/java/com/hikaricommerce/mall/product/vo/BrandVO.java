package com.hikaricommerce.mall.product.vo;

import com.hikaricommerce.mall.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandVO  extends BaseEntity {

  private Long id;
  private String name;
  private String logoUrl;
  private Integer sort;
}
