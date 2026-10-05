package com.hikaricommerce.mall.product.dto;

import com.hikaricommerce.mall.common.dto.PageQuery;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandPageRequest extends PageQuery {

  @Size(max = 64, message = "Brand name must not exceed 64 characters")
  private String name;
}
