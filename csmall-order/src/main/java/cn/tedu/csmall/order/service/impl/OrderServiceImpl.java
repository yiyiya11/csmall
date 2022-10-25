package cn.tedu.csmall.order.service.impl;

import cn.tedu.csmall.order.mapper.OrderMapper;
import cn.tedu.csmall.order.service.IOrderService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper mapper;

    public OrderServiceImpl() {
        log.info("启动OrderServiceImpl");
    }

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        //TODO 1.减少订单中商品的库存数（要调用stock模块功能）

        //TODO 2.从购物车中删除用户勾选的商品(要调用cart模块的功能)

        //TODO 3.将orderAddDTO中的信息转换为Order实体类,然后新增到数据库中
        Order order=new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        mapper.insertOrder(order);
        log.info("新执行的订单信息为:{}",order);
    }
}
