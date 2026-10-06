package com.hikaricommerce.mall.product.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.hikaricommerce.mall.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@TableName(value = "pms_spu", autoResultMap = true)
public class Spu extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String name;

  private Long categoryId;

  @TableField(updateStrategy = FieldStrategy.ALWAYS)
  private Long brandId;

  private Long originPrice;

  private Long price;

  private Integer sales;

  @TableField(updateStrategy = FieldStrategy.ALWAYS)
  private String picUrl;

  @TableField(
    typeHandler = JacksonTypeHandler.class,
    updateStrategy = FieldStrategy.ALWAYS
  )
  private List<String> album;

  @TableField(updateStrategy = FieldStrategy.ALWAYS)
  private String unit;

  @TableField(updateStrategy = FieldStrategy.ALWAYS)
  private String description;

  @TableField(updateStrategy = FieldStrategy.ALWAYS)
  private String detail;

  private Integer status;
}
