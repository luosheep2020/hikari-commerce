package com.hikaricommerce.mall.product.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkuUpdateRequest {

  @NotBlank(message = "SKU code is required")
  @Size(max = 64, message = "SKU code must not exceed 64 characters")
  private String skuSn;

  @NotBlank(message = "SKU name is required")
  @Size(max = 128, message = "SKU name must not exceed 128 characters")
  private String name;

  @NotNull(message = "Price is required")
  @PositiveOrZero(message = "Price must not be negative")
  private Long price;

  @Size(max = 255, message = "Image URL must not exceed 255 characters")
  private String picUrl;
}
