package com.hikaricommerce.mall.product.dto;

import com.hikaricommerce.mall.common.dto.PageQuery;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpuPageRequest extends PageQuery {

  @Size(max = 64, message = "Product name must not exceed 64 characters")
  private String name;

  @Positive(message = "Category ID must be positive")
  private Long categoryId;

  @Positive(message = "Brand ID must be positive")
  private Long brandId;

  @Min(value = 0, message = "Status must be 0 or 1")
  @Max(value = 1, message = "Status must be 0 or 1")
  private Integer status;
}
