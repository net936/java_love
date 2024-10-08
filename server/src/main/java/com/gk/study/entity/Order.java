package com.gk.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("b_order")
public class Order implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    public Long id;
    @TableField
    public String status;
    @TableField
    public String orderTime;
    @TableField
    public String thingA;
    @TableField
    public String thingB;
    @TableField
    public String thingBTitle;
    @TableField
    public String orderNumber; // 编号
    @TableField
    public String receiverAddress;
    @TableField
    public String receiverName;
    @TableField
    public String receiverPhone;
    @TableField
    public String receiverTime;
    @TableField
    public String remark;


}
