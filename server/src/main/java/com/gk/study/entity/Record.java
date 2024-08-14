package com.gk.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@TableName("b_record")
public class Record implements Comparable<Record> {
    @TableId(value = "id",type = IdType.AUTO)
    public Long id;
    @TableField
    public long thingId; // 物品id
    @TableField
    public int score; // 物品分数
    @TableField
    public String ip;  // ip地址

    public Record(){

    }

    public Record(long thingId, int score){
        this.thingId = thingId;
        this.score = score;
    }

    @Override
    public int compareTo(Record o) {
        return score > o.score ? -1 : 1;
    }
}
