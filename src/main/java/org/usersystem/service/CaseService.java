package org.usersystem.service;

import org.usersystem.vo.request.FeedBackRequest;

/**
 * @Author WangQian
 * @Date 2021/6/6 上午 10:31
 */
public interface CaseService {

    /**
     * 添加患者反馈
     *
     * @param request
     */
    void addFeedBack(FeedBackRequest request);
}
