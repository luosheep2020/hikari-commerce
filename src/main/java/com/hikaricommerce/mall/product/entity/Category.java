package com.hikaricommerce.mall.product.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.hikaricommerce.mall.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("pms_category")
public class Category  extends BaseEntity {
  private Long id;
  private String name;
  private Long parentId;
  private Integer level;
  private String iconUrl;
  private Integer sort;
  private Integer visible;
}
