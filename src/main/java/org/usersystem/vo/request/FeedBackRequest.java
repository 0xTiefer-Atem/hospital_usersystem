package org.usersystem.vo.request;

import lombok.Builder;
import lombok.Data;

/**
 * @Author WangQian
 * @Date 2021/6/6 上午 10:36
 */

@Data
@Builder
public class FeedBackRequest {

    /**
     * 预约id
     */
    private String caseId;

    /**
     * 反馈信息
     */
    private String feedBack;
}
