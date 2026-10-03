package com.hikaricommerce.mall.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandSaveRequest {

  @NotBlank(message = "Brand name is required")
  @Size(max = 64, message = "Brand name must not exceed 64 characters")
  private String name;

  @Size(max = 255, message = "Logo URL must not exceed 255 characters")
  private String logoUrl;

  @PositiveOrZero(message = "Sort must be zero or positive")
  private Integer sort;
}
