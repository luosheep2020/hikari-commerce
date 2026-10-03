package com.hikaricommerce.mall.product.service;

import com.hikaricommerce.mall.common.result.PageResult;
import com.hikaricommerce.mall.product.dto.BrandQuery;
import com.hikaricommerce.mall.product.dto.BrandSaveRequest;
import com.hikaricommerce.mall.product.vo.BrandVO;

import java.util.List;

public interface BrandService {
  public BrandVO getBrandById(Long id);
  public List<BrandVO> listBrands();
  public PageResult<BrandVO> pageBrands(BrandQuery query);
  public void createBrand(BrandSaveRequest request);
  public void updateBrand(Long id, BrandSaveRequest request);
  public void deleteBrand(Long id);
}
