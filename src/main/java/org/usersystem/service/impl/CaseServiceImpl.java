package org.usersystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usersystem.mapper.AppointmentInfoMapper;
import org.usersystem.mapper.CaseInfoMapper;
import org.usersystem.mapper.RegisterInfoMapper;
import org.usersystem.service.CaseService;
import org.usersystem.vo.request.FeedBackRequest;

/**
 * @Author WangQian
 * @Date 2021/6/6 上午 10:31
 */

@Service
public class CaseServiceImpl implements CaseService {


    @Autowired
    private AppointmentInfoMapper appointmentInfoMapper;


    @Autowired
    private RegisterInfoMapper registerInfoMapper;

    @Autowired
    private CaseInfoMapper caseInfoMapper;


    /**
     * 添加患者反馈
     *
     * @param request
     */
    @Override
    public void addFeedBack(FeedBackRequest request) {
        String appointmentId = request.getAppointmentId();

        //查询挂号id
        String registerId = registerInfoMapper.getRegisterIdByAppointmentId(appointmentId);


        //根据挂号id更新反馈
        caseInfoMapper.addFeedBackByRegisterId(registerId, request.getFeedBack());
    }
}
