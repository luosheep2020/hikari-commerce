package com.hikaricommerce.mall.product.service;

import com.hikaricommerce.mall.common.result.PageResult;
import com.hikaricommerce.mall.product.dto.BrandPageRequest;
import com.hikaricommerce.mall.product.dto.BrandSaveRequest;
import com.hikaricommerce.mall.product.vo.BrandVO;
import jakarta.validation.Valid;

import java.util.List;

public interface BrandService {
  public BrandVO getBrandById(Long id);
  public List<BrandVO> listBrands();
  PageResult<BrandVO> pageBrands(@Valid BrandPageRequest request);
  public void createBrand(BrandSaveRequest request);
  public void updateBrand(Long id, BrandSaveRequest request);
  public void deleteBrand(Long id);
}
