package com.hikaricommerce.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikaricommerce.mall.common.enums.ErrorCode;
import com.hikaricommerce.mall.common.exception.BusinessException;
import com.hikaricommerce.mall.common.result.PageResult;
import com.hikaricommerce.mall.product.dto.BrandPageRequest;
import com.hikaricommerce.mall.product.dto.BrandSaveRequest;
import com.hikaricommerce.mall.product.entity.Brand;
import com.hikaricommerce.mall.product.repository.BrandRepository;
import com.hikaricommerce.mall.product.service.BrandService;
import com.hikaricommerce.mall.product.vo.BrandVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
  @Resource
  private BrandRepository brandRepository;

  @Override
  public BrandVO getBrandById(Long id) {
    Brand brand = brandRepository.getById(id);
    if (brand == null) {
      throw new BusinessException(ErrorCode.NOT_FOUND,
        "Brand Not Existed");
    }
    BrandVO brandVO = new BrandVO();
    BeanUtils.copyProperties(brand, brandVO);
    return brandVO;
  }

  @Override
  public List<BrandVO> listBrands() {
    return List.of();
  }

  @Override
  public PageResult<BrandVO> pageBrands(BrandPageRequest request) {
    String name = request.getName();
    LambdaQueryWrapper<Brand> lambdaQueryWrapper =
      new LambdaQueryWrapper<Brand>()
        .like(
          StringUtils.hasText(name),
          Brand::getName,
          name == null ? null : name.trim()
        ).orderByDesc(Brand::getId);
    Page<Brand> brandPage = brandRepository.page(
      new Page<>(
        request.getPageNum(),
        request.getPageSize()),
        lambdaQueryWrapper
    );
    List<BrandVO> records=brandPage.getRecords()
      .stream()
      .map(brand -> {
        BrandVO brandVO=new BrandVO();
        BeanUtils.copyProperties(brand,brandVO);
        return brandVO;
      })
      .toList();

    return new PageResult<>(
      brandPage.getCurrent(),
      brandPage.getSize(),
      brandPage.getTotal(),
      records
    );
  }

  @Override
  public void createBrand(BrandSaveRequest request) {
    boolean exists = existsByName(request.getName(), null);

    if (exists) {
      throw new BusinessException(
        ErrorCode.BRAND_NAME_ALREADY_EXISTS
      );
    }

    Brand brand = new Brand();
    BeanUtils.copyProperties(request, brand);
    brandRepository.save(brand);
  }

  @Override
  public void updateBrand(Long id, BrandSaveRequest request) {
    Brand brand = brandRepository.getById(id);
    if (brand == null) {
      throw new BusinessException(
        ErrorCode.BRAND_NOT_FOUND
      );
    }
    boolean exists = existsByName(request.getName(), id);
    if (exists) {
      throw new BusinessException(
        ErrorCode.BRAND_NAME_ALREADY_EXISTS
      );
    }

    BeanUtils.copyProperties(request, brand);
    brandRepository.updateById(brand);
  }

  @Override
  public void deleteBrand(Long id) {
    if (!brandRepository.removeById(id)) {
      throw new BusinessException(ErrorCode.BRAND_NOT_FOUND);
    }
  }

  private boolean existsByName(String name, Long excludeId) {
    return brandRepository.exists(
      new LambdaQueryWrapper<Brand>()
        .eq(Brand::getName, name)
        .ne(excludeId != null, Brand::getId, excludeId)
    );
  }
}
