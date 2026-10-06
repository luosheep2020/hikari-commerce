package com.hikaricommerce.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikaricommerce.mall.common.enums.ErrorCode;
import com.hikaricommerce.mall.common.exception.BusinessException;
import com.hikaricommerce.mall.common.result.PageResult;
import com.hikaricommerce.mall.product.dto.SpuPageRequest;
import com.hikaricommerce.mall.product.dto.SpuSaveRequest;
import com.hikaricommerce.mall.product.dto.SpuStatusRequest;
import com.hikaricommerce.mall.product.entity.Brand;
import com.hikaricommerce.mall.product.entity.Category;
import com.hikaricommerce.mall.product.entity.Sku;
import com.hikaricommerce.mall.product.entity.Spu;
import com.hikaricommerce.mall.product.enums.ProductStatus;
import com.hikaricommerce.mall.product.repository.BrandRepository;
import com.hikaricommerce.mall.product.repository.CategoryRepository;
import com.hikaricommerce.mall.product.repository.SkuRepository;
import com.hikaricommerce.mall.product.repository.SpuRepository;
import com.hikaricommerce.mall.product.service.SpuService;
import com.hikaricommerce.mall.product.vo.SpuVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {
  @Resource
  private SpuRepository spuRepository;
  @Resource
  private CategoryRepository categoryRepository;
  @Resource
  private BrandRepository brandRepository;
  @Resource
  private SkuRepository skuRepository;

  @Override
  public void createSpu(SpuSaveRequest request) {
    Category category = categoryRepository.getById(request.getCategoryId());
    if (category == null) {
      throw new BusinessException(ErrorCode.CATEGORY_NOT_FOUND);
    }

    if (request.getBrandId() != null) {
      Brand brand = brandRepository.getById(request.getBrandId());
      if (brand == null) {
        throw new BusinessException(ErrorCode.BRAND_NOT_FOUND);
      }
    }

    Spu spu = new Spu();
    BeanUtils.copyProperties(request, spu);
    spu.setName(request.getName().trim());
    spu.setSales(0);

    spuRepository.save(spu);
  }

  @Override
  public SpuVO getSpuById(Long id) {
    Spu spu = spuRepository.getById(id);
    if (spu == null) {
      throw new BusinessException(ErrorCode.SPU_NOT_FOUND);
    }
    SpuVO spuVO = new SpuVO();
    BeanUtils.copyProperties(spu, spuVO);
    return spuVO;
  }

  @Override
  public void updateSpu(Long id, SpuSaveRequest request) {
    Spu spu = spuRepository.getById(id);
    if (spu == null) {
      throw new BusinessException(ErrorCode.SPU_NOT_FOUND);
    }

    Category category =
      categoryRepository.getById(request.getCategoryId());

    if (category == null) {
      throw new BusinessException(ErrorCode.CATEGORY_NOT_FOUND);
    }

    if (request.getBrandId() != null) {
      Brand brand = brandRepository.getById(request.getBrandId());

      if (brand == null) {
        throw new BusinessException(ErrorCode.BRAND_NOT_FOUND);
      }
    }

    BeanUtils.copyProperties(request, spu);
    spu.setName(request.getName().trim());

    spuRepository.updateById(spu);
  }

  @Override
  public PageResult<SpuVO> pageSpus(SpuPageRequest request) {
    LambdaQueryWrapper<Spu> wrapper = new LambdaQueryWrapper<Spu>()
      .like(StringUtils.hasText(request.getName()),
        Spu::getName,
        StringUtils.trimAllWhitespace(request.getName()))
      .eq(request.getCategoryId() != null,
        Spu::getCategoryId,
        request.getCategoryId())
      .eq(request.getBrandId() != null,
        Spu::getBrandId,
        request.getBrandId())
      .eq(request.getStatus() != null,
        Spu::getStatus,
        request.getStatus())
      .orderByDesc(Spu::getCreateTime)
      .orderByDesc(Spu::getId);
    Page<Spu> page = new Page<>(
      request.getPageNum(),
      request.getPageSize()
    );
    IPage<Spu> result = spuRepository.page(page, wrapper);

    List<SpuVO> records = result.getRecords().stream()
      .map(spu -> {
        SpuVO spuVO = new SpuVO();
        BeanUtils.copyProperties(spu, spuVO);
        return spuVO;
      }).toList();
    return new PageResult<>(
      result.getCurrent(),
      result.getSize(),
      result.getTotal(),
      records
    );
  }

  @Override
  public void updateSpuStatus(Long id, SpuStatusRequest request) {
    Spu spu = spuRepository.getById(id);
    if (spu == null) {
      throw new BusinessException(ErrorCode.SPU_NOT_FOUND);
    }

    if (Integer.valueOf(ProductStatus.ON_SHELF.getValue())
      .equals(request.getStatus())) {

      boolean hasSkus = skuRepository.exists(
        new LambdaQueryWrapper<Sku>()
          .eq(Sku::getSpuId, id)
      );

      if (!hasSkus) {
        throw new BusinessException(ErrorCode.SPU_HAS_NO_SKUS);
      }
    }

    spuRepository.lambdaUpdate()
      .eq(Spu::getId, id)
      .set(Spu::getStatus, request.getStatus())
      .set(Spu::getUpdateTime, LocalDateTime.now())
      .update();
  }

  @Override
  public void deleteSpu(Long id) {
    Spu spu = spuRepository.getById(id);
    if (spu == null) {
      throw new BusinessException(ErrorCode.SPU_NOT_FOUND);
    }

    if (Integer.valueOf(ProductStatus.ON_SHELF.getValue())
      .equals(spu.getStatus())) {
      throw new BusinessException(ErrorCode.SPU_MUST_BE_OFF_SHELF);
    }

    boolean hasSkus = skuRepository.exists(
      new LambdaQueryWrapper<Sku>()
        .eq(Sku::getSpuId, id)
    );

    if (hasSkus) {
      throw new BusinessException(ErrorCode.SPU_HAS_SKUS);
    }

    spuRepository.removeById(id);
  }
}
