package com.hikaricommerce.mall.product.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySaveRequest {

  @NotBlank(message = "Category name is required")
  @Size(max = 64, message = "Category name must not exceed 64 characters")
  private String name;

  @NotNull(message = "Parent ID is required")
  @PositiveOrZero(message = "Parent ID must be zero or positive")
  private Long parentId = 0L;

  @NotNull(message = "Sort is required")
  @PositiveOrZero(message = "Sort must be zero or positive")
  private Integer sort = 0;

  @NotNull(message = "Status is required")
  @Min(value = 0, message = "Status must be 0 or 1")
  @Max(value = 1, message = "Status must be 0 or 1")
  private Integer status = 1;
}
