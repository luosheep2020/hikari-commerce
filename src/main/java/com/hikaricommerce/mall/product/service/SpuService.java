package com.hikaricommerce.mall.product.service;

import com.hikaricommerce.mall.common.result.PageResult;
import com.hikaricommerce.mall.product.dto.SpuPageRequest;
import com.hikaricommerce.mall.product.dto.SpuSaveRequest;
import com.hikaricommerce.mall.product.dto.SpuStatusRequest;
import com.hikaricommerce.mall.product.vo.SpuVO;

public interface SpuService {

  void createSpu(SpuSaveRequest request);

  SpuVO getSpuById(Long id);

  void updateSpu(Long id, SpuSaveRequest request);

  PageResult<SpuVO> pageSpus(SpuPageRequest request);

  void updateSpuStatus(Long id, SpuStatusRequest request);

  void deleteSpu(Long id);
}
