package com.hikaricommerce.mall.product.controller;

import com.hikaricommerce.mall.common.result.ApiResponse;
import com.hikaricommerce.mall.common.result.PageResult;
import com.hikaricommerce.mall.product.dto.BrandQuery;
import com.hikaricommerce.mall.product.service.BrandService;
import com.hikaricommerce.mall.product.vo.BrandVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/brand")
public class BrandController {
  @Resource
  private BrandService brandService;

  @GetMapping("/{id}")
  public ApiResponse<BrandVO> getBrandById(@PathVariable Long id){
    return ApiResponse.success(brandService.getBrandById(id));
  }

  @GetMapping
  public ApiResponse<PageResult<BrandVO>> pageBrands(
    @Valid @ModelAttribute BrandQuery query) {

    return ApiResponse.success(brandService.pageBrands(query));
  }
}
