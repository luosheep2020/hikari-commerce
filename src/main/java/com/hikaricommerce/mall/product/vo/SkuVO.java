package com.hikaricommerce.mall.product.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SkuVO {

  private Long id;
  private String skuSn;
  private Long spuId;
  private String name;
  private String specIds;
  private Long price;
  private Integer stock;
  private Integer lockedStock;
  private String picUrl;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
