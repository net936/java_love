package com.gk.study.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 协同过滤辅助实体类
 */
public class UserCF {
    public String ip;
    public List<RecEntity> recEntityList = new ArrayList<>();

    public UserCF() {}

    public UserCF(String ip) {
        this.ip = ip;
    }

    public UserCF set(long thingId, int score) {
        this.recEntityList.add(new RecEntity(thingId, score));
        return this;
    }

    public RecEntity find(long thingId) {
        for (RecEntity recEntity : recEntityList) {
            if (recEntity.thingId == thingId) {
                return recEntity;
            }
        }
        return null;
    }

}
