package com.gk.study.service;


import com.gk.study.entity.Ad;
import com.gk.study.entity.Record;

import java.util.List;

public interface RecordService {
    List<Record> getRecordList();
    void createRecord(Record record);
    void updateRecord(Record record);

    Record getRecord(Long thingId, String ip);

    List<String> getRecordIpList();

    List<Record> getRecordListByIp(String ip);
}
