package com.hikaricommerce.mall.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  BAD_REQUEST(400, "Invalid request parameters", HttpStatus.BAD_REQUEST),
  NOT_FOUND(404, "Resource not found", HttpStatus.NOT_FOUND),
  CONFLICT(409, "Data conflict", HttpStatus.CONFLICT),
  INTERNAL_ERROR(500, "Internal server error",
    HttpStatus.INTERNAL_SERVER_ERROR);

  private final int code;
  private final String message;
  private final HttpStatus status;
}
