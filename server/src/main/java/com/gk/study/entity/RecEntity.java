package com.gk.study.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import org.jetbrains.annotations.NotNull;

/**
 * 推荐类
 */
public class RecEntity implements Comparable<RecEntity>{


    @TableField
    public long thingId;
    @TableField
    public int score;


    public RecEntity(long thingId, int score){
        this.thingId = thingId;
        this.score = score;
    }

    @Override
    public int compareTo(@NotNull RecEntity o) {
        return score > o.score ? -1 : 1;
    }
}
