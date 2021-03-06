package com.xxl.job.services;

import com.xxl.job.model.TmsOrderInfo;
import com.xxl.job.vo.TmsOrderInfoVO;

import java.util.List;
import java.util.Map;

/**
 * Created by WIN7 on 2018/4/12.
 */
public interface TmsOrderService {
    List<Map<String, Object>> queryByCustomerOrderSnInList(List<String> orderSns);

    /**
     * 通过运单号获取运单信息
     * @param shippingNo
     * @return
     */
    TmsOrderInfo findOneByShippingNo(String shippingNo);

    /**
     * 根据订单+运单获取订单信息
     * @param shippingNo
     * @param orderId
     * @return
     */
    TmsOrderInfo findOrderByShippingNoAndOrderId(String shippingNo, Integer orderId);


    /**
     * 更新分段运输标记
     * @param tmsOrderInfo
     * @return
     */
    Integer updateSegmentedInteract(TmsOrderInfoVO tmsOrderInfo);


    /**
     * 物流追踪获取订单的线路、承运商物流追踪网址、目的国联系电话信息
     * @param tmsOrderId
     * @param shippingId
     * @return
     */
    TmsOrderInfoVO getOrderShippingInfo(Integer tmsOrderId, Integer shippingId);

    /**
     * 物流追踪获取订单的信息
     * @param tmsOrderId
     * @param shippingId
     * @return
     */
    TmsOrderInfoVO getOrderInfoByCustomerOrderId(Integer tmsOrderId, Integer shippingId);


    /**
     * 物流追踪获取订单的线路、承运商物流追踪网址、目的国联系电话信息
     * @param shippingId
     * @return
     */
    TmsOrderInfoVO getShippingInfoByShippingId(Integer shippingId);

    List<Map<String, Object>> queryByShippingNoAndOrderIdMap(Map<String, Integer> shippingNoOrderIdMap);

    TmsOrderInfo findByTrackingNo(String trackingNo);

    List<Map<String, Object>> queryByCustomerOrderIdInList(List<Integer> orderIds);


    /**
     * 分页获取数据
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<TmsOrderInfoVO> queryByPage(Map<String,Object> params, Integer pageNum, Integer pageSize);
}
