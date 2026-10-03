package com.hikaricommerce.mall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hikaricommerce.mall.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("pms_brand")
public class Brand extends BaseEntity {
  @TableId(type= IdType.AUTO)
  private Long id;
  private String name;
  private String logoUrl;
  private Integer sort;
}
