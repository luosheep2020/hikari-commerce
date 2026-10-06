package com.hikaricommerce.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hikaricommerce.mall.common.enums.ErrorCode;
import com.hikaricommerce.mall.common.exception.BusinessException;
import com.hikaricommerce.mall.product.dto.SkuCreateRequest;
import com.hikaricommerce.mall.product.dto.SkuUpdateRequest;
import com.hikaricommerce.mall.product.entity.Sku;
import com.hikaricommerce.mall.product.entity.Spu;
import com.hikaricommerce.mall.product.enums.ProductStatus;
import com.hikaricommerce.mall.product.repository.SkuRepository;
import com.hikaricommerce.mall.product.repository.SpuRepository;
import com.hikaricommerce.mall.product.service.SkuService;
import com.hikaricommerce.mall.product.vo.SkuVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {
  @Resource
  private SkuRepository skuRepository;
  @Resource
  private SpuRepository spuRepository;

  @Override
  public void createSku(SkuCreateRequest request) {
    Spu spu = spuRepository.getById(request.getSpuId());
    if (spu == null) {
      throw new BusinessException(ErrorCode.SPU_NOT_FOUND);
    }
    if (Integer.valueOf(ProductStatus.ON_SHELF.getValue())
      .equals(spu.getStatus())) {
      throw new BusinessException(ErrorCode.CONFLICT, " Product must be taken off shelf before adding a SKU");
    }
    String skuSn = request.getSkuSn().trim();

    boolean exists = skuRepository.exists(
      new LambdaQueryWrapper<Sku>()
        .eq(Sku::getSkuSn, skuSn)
    );

    if (exists) {
      throw new BusinessException(ErrorCode.SKU_SN_ALREADY_EXISTS);
    }

    Sku sku = new Sku();
    BeanUtils.copyProperties(request, sku);
    sku.setSkuSn(skuSn);
    sku.setName(request.getName().trim());
    sku.setLockedStock(0);

    skuRepository.save(sku);
  }

  @Override
  public SkuVO getSkuById(Long id) {
    Sku sku = skuRepository.getById(id);
    if (sku == null) {
      throw new BusinessException(ErrorCode.SKU_NOT_FOUND);
    }

    SkuVO vo = new SkuVO();
    BeanUtils.copyProperties(sku, vo);
    return vo;
  }

  @Override
  public List<SkuVO> listSkusBySpuId(Long spuId) {
    Spu spu = spuRepository.getById(spuId);
    if (spu == null) {
      throw new BusinessException(ErrorCode.SPU_NOT_FOUND);
    }
    List<Sku> skuList = skuRepository.list(
      new LambdaQueryWrapper<Sku>()
        .eq(Sku::getSpuId, spuId)
        .orderByAsc(Sku::getId)
    );
    return skuList.stream()
      .map(sku -> {
        SkuVO skuVO = new SkuVO();
        BeanUtils.copyProperties(sku, skuVO);
        return skuVO;
      }).toList();
  }

  @Override
  public void updateSku(Long id, SkuUpdateRequest request) {
    Sku sku = skuRepository.getById(id);
    if (sku == null) {
      throw new BusinessException(ErrorCode.SKU_NOT_FOUND);
    }
    String skuSn = sku.getSkuSn().trim();
    boolean exists = skuRepository.exists(
      new LambdaQueryWrapper<Sku>()
        .eq(Sku::getSkuSn, skuSn)
        .ne(Sku::getId, id)
    );
    if (exists) {
      throw new BusinessException(ErrorCode.SKU_SN_ALREADY_EXISTS);
    }
    BeanUtils.copyProperties(request, sku);
    sku.setSkuSn(skuSn);
    sku.setName(request.getName().trim());
    skuRepository.updateById(sku);
  }

  @Override
  public void deleteSku(Long id) {
    Sku sku = skuRepository.getById(id);
    if (sku == null) {
      throw new BusinessException(ErrorCode.SKU_NOT_FOUND);
    }
    Spu spu = spuRepository.getById(sku.getSpuId());
    if (spu == null) {
      throw new BusinessException(ErrorCode.SPU_NOT_FOUND);
    }
    if (Integer.valueOf(ProductStatus.ON_SHELF.getValue())
      .equals(spu.getStatus())) {
      throw new BusinessException(
        ErrorCode.CONFLICT,
        "Product must be taken off shelf before deleting a SKU"
      );
    }
    if (sku.getLockedStock() != null && sku.getLockedStock() > 0) {
      throw new BusinessException(ErrorCode.SKU_HAS_LOCKED_STOCK);
    }
    skuRepository.removeById(id);
  }
}
