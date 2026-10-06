package com.hikaricommerce.mall.product.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {

  OFF_SHELF(0),
  ON_SHELF(1);

  private final int value;
}
