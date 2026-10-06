package com.hikaricommerce.mall.product.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SpuVO {

  private Long id;
  private String name;
  private Long categoryId;
  private Long brandId;
  private Long originPrice;
  private Long price;
  private Integer sales;
  private String picUrl;
  private List<String> album;
  private String unit;
  private String description;
  private String detail;
  private Integer status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
