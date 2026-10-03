package com.hikaricommerce.mall.common.result;

import java.util.List;

public record PageResult<T>(
  long pageNum,
  long pageSize,
  long total,
  List<T> records
) {
}
