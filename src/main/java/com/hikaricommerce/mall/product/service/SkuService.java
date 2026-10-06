package com.hikaricommerce.mall.product.service;

import com.hikaricommerce.mall.product.dto.SkuCreateRequest;
import com.hikaricommerce.mall.product.dto.SkuUpdateRequest;
import com.hikaricommerce.mall.product.vo.SkuVO;

import java.util.List;

public interface SkuService {
  public 	void createSku(SkuCreateRequest request);
  public SkuVO getSkuById(Long id);
  public List<SkuVO> listSkusBySpuId(Long spuId);
  public 	void updateSku(Long id, SkuUpdateRequest request);
  public void deleteSku(Long id);
}
