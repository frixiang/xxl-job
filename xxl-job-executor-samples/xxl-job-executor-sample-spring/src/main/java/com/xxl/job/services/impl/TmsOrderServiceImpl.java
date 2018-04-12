package com.xxl.job.services.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xxl.job.dao.TmsOrderInfoDao;
import com.xxl.job.enums.DatasourceEnum;
import com.xxl.job.model.TmsOrderInfo;
import com.xxl.job.page.PageInfo;
import com.xxl.job.page.Pagination;
import com.xxl.job.services.TmsOrderService;
import com.xxl.job.vo.TmsOrderInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WIN7 on 2018/4/12.
 */
@Service
public class TmsOrderServiceImpl implements TmsOrderService {

    @Autowired
    private TmsOrderInfoDao tmsOrderInfoDao;

    @Override
    public List<Map<String, Object>> queryByCustomerOrderIdInList(List<Integer> orderIds) {
        return tmsOrderInfoDao.queryByCustomerOrderIdInList(orderIds);
    }

    @Override
    public List<Map<String, Object>> queryByCustomerOrderSnInList(List<String> orderSns){
        return tmsOrderInfoDao.queryByCustomerOrderSnInList(orderSns);
    }

    /**
     * 物流追踪获取订单的线路、承运商物流追踪网址、目的国联系电话信息
     * @param orderId
     * @param shippingId
     * @return
     */
    @Override
    public TmsOrderInfoVO getOrderShippingInfo(Integer orderId, Integer shippingId){
        return tmsOrderInfoDao.getOrderShippingInfo(orderId,shippingId);
    }

    /**
     * 物流追踪获取订单的信息
     * @param tmsOrderId
     * @param shippingId
     * @return
     */
    @Override
    public TmsOrderInfoVO getOrderInfoByCustomerOrderId(Integer tmsOrderId,Integer shippingId){
        return tmsOrderInfoDao.getOrderInfoByCustomerOrderId(tmsOrderId, shippingId);
    }


    /**
     * 物流追踪获取订单的线路、承运商物流追踪网址、目的国联系电话信息
     * @param shippingId
     * @return
     */
    @Override
    public TmsOrderInfoVO getShippingInfoByShippingId(Integer shippingId){
        return tmsOrderInfoDao.getShippingInfoByShippingId(shippingId);
    }

    @Override
    public List<Map<String, Object>> queryByShippingNoAndOrderIdMap(Map<String, Integer> shippingNoOrderIdMap) {
        return tmsOrderInfoDao.queryByShippingNoAndOrderIdMap(shippingNoOrderIdMap);
    }

    @Override
    public TmsOrderInfo findByTrackingNo(String trackingNo) {
        return tmsOrderInfoDao.findByTrackingNo(trackingNo);
    }

    /**
     * 通过运单号获取运单信息
     * @param shippingNo
     * @return
     */
    @Override
    public TmsOrderInfo findOneByShippingNo(String shippingNo){
        return tmsOrderInfoDao.findOneByShippingNo(shippingNo);
    }

    /**
     * 根据订单+运单获取订单信息
     * @param shippingNo
     * @param orderId
     * @return
     */
    @Override
    public TmsOrderInfo findOrderByShippingNoAndOrderId(String shippingNo, Integer orderId){
        return tmsOrderInfoDao.findOrderByShippingNoAndOrderId(shippingNo, orderId);
    }

    /**
     * 更新分段运输标记
     * @param tmsOrderInfo
     * @return
     */
    @Override
    public Integer updateSegmentedInteract(TmsOrderInfo tmsOrderInfo){
        return tmsOrderInfoDao.updateSegmentedInteract(tmsOrderInfo);
    }

    /**
     * 分页获取数据
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<TmsOrderInfoVO> queryByPage(Map<String,Object> params, Integer pageNum, Integer pageSize){
        PageInfo<TmsOrderInfoVO> pageInfo = new PageInfo<>(pageNum, pageSize);
        List<TmsOrderInfoVO> list = Lists.newArrayList();
        Long count = tmsOrderInfoDao.queryTmsOrderInfoVOCountByParams(params);
        if(count > 0){
            params.put("offset",pageInfo.getFrom());
            params.put("max",pageInfo.getSize());
            list = tmsOrderInfoDao.queryTmsOrderInfoVOListByParams(params);
        }
        return list;
    }
}
