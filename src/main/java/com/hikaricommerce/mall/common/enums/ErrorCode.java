package com.hikaricommerce.mall.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  // 通用错误
  BAD_REQUEST(
    400, "Invalid request parameters", HttpStatus.BAD_REQUEST
  ),
  NOT_FOUND(
    404, "Resource not found", HttpStatus.NOT_FOUND
  ),
  CONFLICT(
    409, "Data conflict", HttpStatus.CONFLICT
  ),
  INTERNAL_ERROR(
    500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR
  ),

  // 品牌
  BRAND_NOT_FOUND(
    10001, "Brand not found", HttpStatus.NOT_FOUND
  ),
  BRAND_NAME_ALREADY_EXISTS(
    10002, "Brand name already exists", HttpStatus.CONFLICT
  ),
  BRAND_HAS_PRODUCTS(
    10003, "Brand has associated products", HttpStatus.CONFLICT
  ),

  // 分类
  CATEGORY_NOT_FOUND(
    11001, "Category not found", HttpStatus.NOT_FOUND
  ),
  CATEGORY_PARENT_NOT_FOUND(
    11002, "Parent category not found", HttpStatus.BAD_REQUEST
  ),
  CATEGORY_NAME_ALREADY_EXISTS(
    11003, "Category name already exists under this parent",
    HttpStatus.CONFLICT
  ),
  CATEGORY_PARENT_IS_SELF(
    11004, "Category cannot be its own parent",
    HttpStatus.BAD_REQUEST
  ),
  CATEGORY_PARENT_IS_DESCENDANT(
    11005, "Category parent cannot be its descendant",
    HttpStatus.BAD_REQUEST
  ),
  CATEGORY_HAS_CHILDREN(
    11006, "Category has child categories", HttpStatus.CONFLICT
  ),
  CATEGORY_HAS_PRODUCTS(
    11007, "Category has associated products", HttpStatus.CONFLICT
  ),
  CATEGORY_HIERARCHY_INVALID(
    11008, "Category hierarchy is invalid",
    HttpStatus.INTERNAL_SERVER_ERROR
  );

  private final int code;
  private final String message;
  private final HttpStatus status;
}
