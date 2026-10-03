package com.hikaricommerce.mall.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity {
  @TableField(fill = FieldFill.INSERT)
  private String createTime;

  @TableField(fill = FieldFill.UPDATE)
  private String updateTime;
}
