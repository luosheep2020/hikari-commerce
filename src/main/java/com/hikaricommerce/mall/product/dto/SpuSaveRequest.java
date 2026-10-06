package com.hikaricommerce.mall.product.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpuSaveRequest {

  @NotBlank(message = "Product name is required")
  @Size(max = 64, message = "Product name must not exceed 64 characters")
  private String name;

  @NotNull(message = "Category ID is required")
  @Positive(message = "Category ID must be positive")
  private Long categoryId;

  @Positive(message = "Brand ID must be positive")
  private Long brandId;

  @NotNull(message = "Original price is required")
  @PositiveOrZero(message = "Original price must not be negative")
  private Long originPrice;

  @NotNull(message = "Price is required")
  @PositiveOrZero(message = "Price must not be negative")
  private Long price;

  @Size(max = 255, message = "Image URL must not exceed 255 characters")
  private String picUrl;

  private List<
    @NotBlank(message = "Album image URL is required")
    @Size(max = 255, message = "Album image URL must not exceed 255 characters")
      String
    > album;

  @Size(max = 16, message = "Unit must not exceed 16 characters")
  private String unit;

  @Size(max = 255, message = "Description must not exceed 255 characters")
  private String description;

  private String detail;

}
