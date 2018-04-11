package com.xxl.job.vo;

import com.xxl.job.model.TmsOrderInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmsOrderInfoVO extends TmsOrderInfo{
    /**
     * 线路名称
     */
    private String shippingName;

    /**
     *  总重量
     */
    private Double totalWeight;

    /**
     * 国家ID
     */
    private Integer country;

    /**
     * 国家名称
     */
    private String countryName;


    /**
     * 目的国联系电话
     */
    private String telephone;

    /**
     * 终端承运商物流追踪网址
     */
    private String trackLink;

    /**
     * 包裹数量
     */
    private Integer packageNum;

    /**
     * customerOrderId
     */
    private Integer customerOrderId;

    /**
     * 承运商编码
     */
    private String code;

    private Byte interactType;
    private String interactTypeName;
}
