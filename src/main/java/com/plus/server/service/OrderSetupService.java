package com.plus.server.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plus.server.common.PageDefault;
import com.plus.server.dal.OrderCounterDAO;
import com.plus.server.dal.OrderCounterDetailDAO;
import com.plus.server.dal.OrderSetupDAO;
import com.plus.server.dal.SetupRecordDAO;
import com.plus.server.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mahui on 16/7/27.
 */
@Service
public class OrderSetupService {
    private static final Logger log = LoggerFactory.getLogger(OrderSetupService.class);
    @Autowired
    private OrderCounterDAO orderCounterDAO;
    @Autowired
    private OrderCounterDetailDAO orderCounterDetailDAO;

    @Autowired
    private OrderSetupDAO orderSetupDAO;

    @Autowired
    private SetupRecordDAO setupRecordDAO;

    public OrderSetup selectById(Long id) throws Exception{
        log.info("查询生产加工单，id=" + id);
        if(id == null){
            throw new Exception("参数为null");
        }
        return this.orderSetupDAO.selectByPrimaryKey(id);
    }

    public List<SetupRecord> selectDtlById(Long dtlId) throws Exception{
        log.info("查询柜台任务单明细，dtlId=" + dtlId);
        if(dtlId == null){
            throw new Exception("参数为null");
        }
        SetupRecord setR = new SetupRecord();
        setR.setOrderSetupId(dtlId);
        return this.setupRecordDAO.selectByModel(setR);
    }
}
