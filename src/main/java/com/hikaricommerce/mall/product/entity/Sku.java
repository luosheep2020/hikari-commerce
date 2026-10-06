package com.hikaricommerce.mall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hikaricommerce.mall.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("pms_sku")
public class Sku extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long id;
  private String skuSn;
  private Long spuId;
  private String name;
  private String specIds;
  private Long price;
  private Integer stock;
  private Integer lockedStock;
  private String picUrl;
}
