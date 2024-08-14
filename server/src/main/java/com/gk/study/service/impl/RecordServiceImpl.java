package com.gk.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.study.entity.Record;
import com.gk.study.mapper.RecordMapper;
import com.gk.study.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordMapper mapper;
    @Override
    public List<Record> getRecordList() {
        return null;
    }

    @Override
    public void createRecord(Record record) {
        mapper.insert(record);
    }

    @Override
    public void updateRecord(Record record) {
        mapper.updateById(record);
    }

    @Override
    public Record getRecord(Long thingId, String ip) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ip", ip);
        queryWrapper.eq("thing_id", thingId);
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public List<String> getRecordIpList() {
        return mapper.getIpList();
    }

    @Override
    public List<Record> getRecordListByIp(String ip) {
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ip", ip);
        return mapper.selectList(queryWrapper);
    }
}
