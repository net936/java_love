package com.gk.study.service;


import com.gk.study.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getFeedbackList();
    void createFeedback(Feedback feedback);
    void deleteFeedback(String id);
}
