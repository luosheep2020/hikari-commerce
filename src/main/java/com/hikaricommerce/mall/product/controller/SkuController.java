package com.hikaricommerce.mall.product.controller;

import com.hikaricommerce.mall.common.result.ApiResponse;
import com.hikaricommerce.mall.product.dto.SkuCreateRequest;
import com.hikaricommerce.mall.product.dto.SkuUpdateRequest;
import com.hikaricommerce.mall.product.service.SkuService;
import com.hikaricommerce.mall.product.vo.SkuVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/sku")
public class SkuController {

  @Resource
  private SkuService skuService;

  @PostMapping
  public ApiResponse<Void> createSku(
    @Valid @RequestBody SkuCreateRequest request
  ) {
    skuService.createSku(request);
    return ApiResponse.success();
  }

  @GetMapping("/{id}")
  public ApiResponse<SkuVO> getSkuById(@PathVariable Long id) {
    return ApiResponse.success(skuService.getSkuById(id));
  }

  @GetMapping
  public ApiResponse<List<SkuVO>> listSkusBySpuId(
    @RequestParam Long spuId
  ) {
    return ApiResponse.success(skuService.listSkusBySpuId(spuId));
  }

  @PutMapping("/{id}")
  public ApiResponse<Void> updateSku(
    @PathVariable Long id,
    @Valid @RequestBody SkuUpdateRequest request
  ) {
    skuService.updateSku(id, request);
    return ApiResponse.success();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> deleteSku(@PathVariable Long id) {
    skuService.deleteSku(id);
    return ApiResponse.success();
  }
}
