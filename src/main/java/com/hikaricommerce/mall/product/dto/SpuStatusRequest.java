package com.hikaricommerce.mall.product.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpuStatusRequest {

  @NotNull(message = "Status is required")
  @Min(value = 0, message = "Status must be 0 or 1")
  @Max(value = 1, message = "Status must be 0 or 1")
  private Integer status;
}
