package com.plus.server.service;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plus.server.common.PageDefault;
import com.plus.server.dal.OrderCounterDAO;
import com.plus.server.dal.OrderCounterDetailDAO;
import com.plus.server.model.OrderCounter;
import com.plus.server.model.OrderCounterDetail;
import com.plus.server.model.OrderSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mahui on 16/7/27.
 */
@Service
public class OrderCounterService {
    private static final Logger log = LoggerFactory.getLogger(OrderCounterService.class);
    @Autowired
    private OrderCounterDAO orderCounterDAO;
    @Autowired
    private OrderCounterDetailDAO orderCounterDetailDAO;


    public PageInfo<OrderCounter> selectByModel(OrderCounter orderCounter, Integer page, Integer pageSize) {
        log.info("查询柜台任务单，orderSupplier=" + JSON.toJSONString(orderCounter));
        if(page == null || page <= 0){
            page = PageDefault.PAGE_NUM_DEFAULT;
        }
        if(pageSize == null || pageSize <= 0){
            pageSize = PageDefault.PAGE_SIZE_DEFAULT;
        }
        if(orderCounter.getValid() == null){
            orderCounter.setValid(1);
        }
        PageHelper.startPage(page, pageSize);
        List<OrderCounter> orderList = this.orderCounterDAO.selectByModelLike(orderCounter);
        PageInfo<OrderCounter> pageInfo = new PageInfo<OrderCounter>(orderList);
        return pageInfo;
    }

    public OrderCounter selectById(Long id) throws Exception{
        log.info("查询生产加工单，id=" + id);
        if(id == null){
            throw new Exception("参数为null");
        }
        return this.orderCounterDAO.selectByPrimaryKey(id);
    }

    public List<OrderCounter> selectByModel(OrderCounter orderCounter) {
        log.info("查询柜台任务单，orderCounter=" + JSON.toJSONString(orderCounter));
        if(orderCounter.getValid() == null){
            orderCounter.setValid(1);
        }
        List<OrderCounter> orderList = this.orderCounterDAO.selectByModelLike(orderCounter);
        return orderList;
    }

    public OrderCounterDetail selectDtlById(Long dtlId) throws Exception{
        log.info("查询柜台任务单明细，dtlId=" + dtlId);
        if(dtlId == null){
            throw new Exception("参数为null");
        }
        return this.orderCounterDetailDAO.selectByPrimaryKey(dtlId);
    }


    public List<OrderCounterDetail> selectDtlByPid(Long orderCounterId) throws Exception{
        log.info("查询柜台任务单明细，orderCounterId=" + orderCounterId);
        if(orderCounterId == null){
            throw new Exception("参数为null");
        }
        OrderCounterDetail param = new OrderCounterDetail();
        param.setValid(1);
        param.setOrderCounterId(orderCounterId);
        return this.orderCounterDetailDAO.selectByModel(param);
    }

    public void updateDtl(OrderCounterDetail dtl) throws Exception{
        log.info("更新明细，dtl=" + JSON.toJSONString(dtl));
        if(dtl == null){
            throw new Exception("参数为null");
        }
        this.orderCounterDetailDAO.updateByPrimaryKeySelective(dtl);
    }
}
