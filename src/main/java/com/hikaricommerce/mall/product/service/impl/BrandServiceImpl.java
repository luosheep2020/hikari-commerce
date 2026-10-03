package com.hikaricommerce.mall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hikaricommerce.mall.common.enums.ErrorCode;
import com.hikaricommerce.mall.common.exception.BusinessException;
import com.hikaricommerce.mall.common.result.PageResult;
import com.hikaricommerce.mall.product.dto.BrandQuery;
import com.hikaricommerce.mall.product.dto.BrandSaveRequest;
import com.hikaricommerce.mall.product.entity.Brand;
import com.hikaricommerce.mall.product.repository.BrandRepository;
import com.hikaricommerce.mall.product.service.BrandService;
import com.hikaricommerce.mall.product.vo.BrandVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
  public PageResult<BrandVO> pageBrands(BrandQuery query) {
    IPage<Brand> page = brandRepository.pageBrands(query);

    List<BrandVO> records = page.getRecords()
      .stream()
      .map(brand -> {
        BrandVO brandVO=new BrandVO();
        BeanUtils.copyProperties(brand,brandVO);
        return brandVO;
      })
      .toList();
    return new PageResult<>(
      page.getCurrent(),
      page.getSize(),
      page.getTotal(),
      records
    );
  }

  @Override
  public void createBrand(BrandSaveRequest request) {

  }

  @Override
  public void updateBrand(Long id, BrandSaveRequest request) {

  }

  @Override
  public void deleteBrand(Long id) {

  }
}
