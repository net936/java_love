package com.gk.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.study.entity.Feedback;
import com.gk.study.mapper.FeedbackMapper;
import com.gk.study.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public List<Feedback> getFeedbackList() {
        return feedbackMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void createFeedback(Feedback feedback) {
        feedbackMapper.insert(feedback);
    }

    @Override
    public void deleteFeedback(String id) {
        feedbackMapper.deleteById(id);
    }
}
