package com.hikaricommerce.mall.product.controller;

import com.hikaricommerce.mall.common.result.ApiResponse;
import com.hikaricommerce.mall.common.result.PageResult;
import com.hikaricommerce.mall.product.dto.SpuPageRequest;
import com.hikaricommerce.mall.product.dto.SpuSaveRequest;
import com.hikaricommerce.mall.product.dto.SpuStatusRequest;
import com.hikaricommerce.mall.product.service.SpuService;
import com.hikaricommerce.mall.product.vo.SpuVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/spu")
public class SpuController {

  @Resource
  private SpuService spuService;

  @GetMapping("/{id}")
  public ApiResponse<SpuVO> getSpuById(@PathVariable Long id) {
    return ApiResponse.success(spuService.getSpuById(id));
  }

  @GetMapping
  public ApiResponse<PageResult<SpuVO>> pageSpus(
    @Valid @ModelAttribute SpuPageRequest request
  ) {
    return ApiResponse.success(spuService.pageSpus(request));
  }

  @PostMapping
  public ApiResponse<Void> createSpu(
    @Valid @RequestBody SpuSaveRequest request
  ) {
    spuService.createSpu(request);
    return ApiResponse.success();
  }

  @PutMapping("/{id}")
  public ApiResponse<Void> updateSpu(
    @PathVariable Long id,
    @Valid @RequestBody SpuSaveRequest request
  ) {
    spuService.updateSpu(id, request);
    return ApiResponse.success();
  }

  @PatchMapping("/{id}/status")
  public ApiResponse<Void> updateSpuStatus(
    @PathVariable Long id,
    @Valid @RequestBody SpuStatusRequest request
  ) {
    spuService.updateSpuStatus(id, request);
    return ApiResponse.success();
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> deleteSpu(@PathVariable Long id) {
    spuService.deleteSpu(id);
    return ApiResponse.success();
  }
}
