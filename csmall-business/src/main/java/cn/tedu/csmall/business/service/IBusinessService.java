package cn.tedu.csmall.business.service;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;

public interface IBusinessService {

    /**
     * 声明触发新增订单业务的方法
     */
    void buy(OrderAddDTO orderAddDTO);
}
