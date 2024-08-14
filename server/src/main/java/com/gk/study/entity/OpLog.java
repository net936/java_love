package com.gk.study.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("b_op_log")
public class OpLog implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    public Long id;
    @TableField
    public String reIp; // 请求ip
    @TableField
    public String reTime; // 请求时间
    @TableField
    public String reUa; // 请求UA
    @TableField
    public String reUrl; // 请求url
    @TableField
    public String reMethod; // 请求方法
    @TableField
    public String reContent; // 请求内容
    @TableField
    public String accessTime;

}
