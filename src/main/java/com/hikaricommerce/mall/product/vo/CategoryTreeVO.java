package com.hikaricommerce.mall.product.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryTreeVO {

  private Long id;
  private String name;
  private Long parentId;
  private Integer sort;
  private Integer visible;
  private List<CategoryTreeVO> children = new ArrayList<>();
}
