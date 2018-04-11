package com.xxl.job.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmsOrderInfo {
    private Integer tmsOrderId;

    private String waybillNo;

    private Integer customerOrderId;

    private Integer shippingId;
    //物流追踪号
    private String trackingNo;
    //仓库编号
    private Integer depotId;
    //订单类型，0:非COD订单，1:COD订单
    private Integer orderType;
    //汇率
    private BigDecimal rate;
    //下单时所选的货币
    private String currency;
    //cod金额
    private BigDecimal codAmount;
    // 税费
    private Double tax;
    // 运费
    private BigDecimal shippingFee;
    //运单状态：0:已预报，1:仓库已签收，2:已入库，3:已出库，4:已离港，5:已抵达，6:已妥投
    private Byte shippingStatus;

    //客户账号，不同的结算主体对应不同的客户账号
    private String customerAccount;
    //线路编码，对应tms_shipping表中的shipping_code字段
    private String routeCode;

    private Integer createdTime;

    private Integer shippedTime;

    private Integer departureTime;

    private Integer arrivalTime;

    private Integer deliveredTime;

    private Integer gmtCreated;

    private Integer gmtModified;

    private Integer operatorId;

    private Integer isInteracted;
    private Long interactTime;

    private String sinoairNo;

    private Byte siteId;

    private Integer selfStoreId;

    private Byte selfPickUp;

    private Integer languageSite;

    private  String customerOrderSn;

    private Integer goodsNum;

    /**
     * 订单代收金额
     */
    private BigDecimal orderAmount;

}
