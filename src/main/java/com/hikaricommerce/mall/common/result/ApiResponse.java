package com.hikaricommerce.mall.common.result;

public record ApiResponse<T>(
  Integer code,
  String message,
  Object data
) {
  public static <T> ApiResponse<T> success(T data){
    return new ApiResponse<>(0,"success",data);
  }


  public static ApiResponse<Void> success() {
    return new ApiResponse<>(0, "success", null);
  }

  public static ApiResponse<Void> failure(int code, String message) {
    return new ApiResponse<>(code, message, null);
  }
}
