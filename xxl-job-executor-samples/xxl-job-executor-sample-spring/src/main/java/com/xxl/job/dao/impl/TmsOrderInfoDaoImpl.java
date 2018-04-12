package com.xxl.job.dao.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.xxl.job.dao.TmsOrderInfoDao;
import com.xxl.job.enums.DatasourceEnum;
import com.xxl.job.model.TmsOrderInfo;
import com.xxl.job.page.Pagination;
import com.xxl.job.vo.TmsOrderInfoVO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TmsOrderInfoDaoImpl extends BaseDaoImpl<TmsOrderInfo> implements TmsOrderInfoDao {

    @Override
    protected DatasourceEnum getSqlSessionType() {
        return DatasourceEnum.TMS;
    }


    @Override
    public List<Map<String, Object>> queryByCustomerOrderIdInList(List<Integer> orderIds) {
        if (CollectionUtils.isEmpty(orderIds)) {
            return new ArrayList<>();
        }
        return getSqlSession().selectList(this.clazz.getName() + "Mapper.queryByCustomerOrderIdInList", orderIds);
    }

    @Override
    public List<Map<String, Object>> queryByCustomerOrderSnInList(List<String> orderSns){
        return null;
    }

    /**
     * 物流追踪获取订单的线路、承运商物流追踪网址、目的国联系电话信息
     * @param orderId
     * @param shippingId
     * @return
     */
    @Override
    public TmsOrderInfoVO getOrderShippingInfo(Integer orderId, Integer shippingId){
        Map<String,Object> params = Maps.newHashMap();
        params.put("orderId",orderId);
        params.put("shippingId",shippingId);
        return getSqlSession().selectOne(this.clazz.getName() + "Mapper.getOrderShippingInfo",params);
    }

    /**
     * 物流追踪获取订单的信息
     * @param tmsOrderId
     * @param shippingId
     * @return
     */
    @Override
    public TmsOrderInfoVO getOrderInfoByCustomerOrderId(Integer tmsOrderId,Integer shippingId){
        Map<String,Object> params = Maps.newHashMap();
        params.put("orderId",tmsOrderId);
        params.put("shippingId",shippingId);
        return getSqlSession().selectOne(this.clazz.getName() + "Mapper.getOrderInfoByCustomerOrderId",params);
    }


    /**
     * 物流追踪获取订单的线路、承运商物流追踪网址、目的国联系电话信息
     * @param shippingId
     * @return
     */
    @Override
    public TmsOrderInfoVO getShippingInfoByShippingId(Integer shippingId){
        Map<String,Object> params = Maps.newHashMap();
        params.put("shippingId",shippingId);
        return getSqlSession().selectOne(this.clazz.getName() + "Mapper.getShippingInfoByShippingId",params);
    }

    @Override
    public List<Map<String, Object>> queryByShippingNoAndOrderIdMap(Map<String, Integer> shippingNoOrderIdMap) {
        if (shippingNoOrderIdMap.size() == 0) {
            return new ArrayList<>();
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("entryParam", shippingNoOrderIdMap.entrySet());
        return getSqlSession().selectList(this.clazz.getName() + "Mapper.queryByShippingNoAndOrderIdMap", params);
    }

    @Override
    public TmsOrderInfo findByTrackingNo(String trackingNo) {
        Map<String,Object> params = Maps.newHashMap();
        params.put("trackingNo",trackingNo);
        return getSqlSession().selectOne(this.clazz.getName() + "Mapper.findByTrackingNo",params);
    }

    /**
     * 通过运单号获取运单信息
     * @param shippingNo
     * @return
     */
    @Override
    public TmsOrderInfo findOneByShippingNo(String shippingNo){
        return null;
    }

    /**
     * 根据订单+运单获取订单信息
     * @param shippingNo
     * @param orderId
     * @return
     */
    @Override
    public TmsOrderInfo findOrderByShippingNoAndOrderId(String shippingNo, Integer orderId){
        return null;
    }

    /**
     * 更新分段运输标记
     * @param tmsOrderInfo
     * @return
     */
    @Override
    public Integer updateSegmentedInteract(TmsOrderInfo tmsOrderInfo){
        return 0;
    }

    @Override
    public List<TmsOrderInfoVO> queryTmsOrderInfoVOListByParams(Map<String, Object> searchParam) {
        return getSqlSession().selectList(this.clazz.getName() + "Mapper.queryTmsOrderInfoVOListByParams", searchParam);
    }

    @Override
    public Long queryTmsOrderInfoVOCountByParams(Map<String, Object> searchParam) {
        return getSqlSession().selectOne(this.clazz.getName() + "Mapper.queryTmsOrderInfoVOCountByParams", searchParam);
    }

}
