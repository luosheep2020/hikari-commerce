package com.hikaricommerce.mall.product.controller;

import com.hikaricommerce.mall.common.result.ApiResponse;
import com.hikaricommerce.mall.common.result.PageResult;
import com.hikaricommerce.mall.product.dto.BrandPageRequest;
import com.hikaricommerce.mall.product.dto.BrandSaveRequest;
import com.hikaricommerce.mall.product.service.BrandService;
import com.hikaricommerce.mall.product.vo.BrandVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/brand")
public class BrandController {

  @Resource
  private BrandService brandService;

  @GetMapping("/{id}")
  public ApiResponse<BrandVO> getBrandById(@PathVariable Long id) {
    return ApiResponse.success(brandService.getBrandById(id));
  }

  @GetMapping
  public ApiResponse<PageResult<BrandVO>> pageBrands(
    @Valid @ModelAttribute BrandPageRequest request
  ) {
    return ApiResponse.success(brandService.pageBrands(request));
  }

  @PostMapping
  public ApiResponse<Void> createBrand(
    @Valid @RequestBody BrandSaveRequest request
  ) {
    brandService.createBrand(request);
    return ApiResponse.success();
  }

  @PutMapping("/{id}")
  public ApiResponse<Void> updateBrand(
    @PathVariable Long id,
    @Valid @RequestBody BrandSaveRequest request
  ) {
    brandService.updateBrand(id, request);
    return ApiResponse.success();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> deleteBrand(@PathVariable Long id) {
    brandService.deleteBrand(id);
    return ApiResponse.success();
  }
}
