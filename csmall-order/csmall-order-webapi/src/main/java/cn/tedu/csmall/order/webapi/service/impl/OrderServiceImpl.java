package cn.tedu.csmall.order.webapi.service.impl;

import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.order.service.IOrderService;
import cn.tedu.csmall.order.webapi.mapper.OrderMapper;
import cn.tedu.csmall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// order模块的方法会被business模块调用,所以也是生产者要加@DubboService注解
@DubboService
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    // 添加@DubboReference注解,表示当前业务逻辑层中需要消费其他模块的服务
    // 声明的接口应该是其他服务提供的业务逻辑层接口
    // 因为Nacos中注册了业务的实现类,所以声明的接口会自动匹配到实现类对象
    // 我们需要两个模块的实现类,stock和cart
    @DubboReference
    private IStockService stockService;
    @DubboReference
    private ICartService cartService;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        // 1.减少订单中商品的库存数(要调用stock模块的功能)
        // 先实例化业务逻辑层需要的指定类型DTO才能调用
        StockReduceCountDTO countDTO=new StockReduceCountDTO();
        countDTO.setCommodityCode(orderAddDTO.getCommodityCode());
        countDTO.setReduceCount(orderAddDTO.getCount());
        // dubbo调用stock执行减少库存的方法
        stockService.reduceCommodityCount(countDTO);
        // 2.从购物车中删除用户勾选的商品(要调用cart模块的功能)
        cartService.deleteUserCart(orderAddDTO.getUserId(),
                                    orderAddDTO.getCommodityCode());

/*        if(Math.random()<0.5){
            throw new CoolSharkServiceException(
                    ResponseCode.INTERNAL_SERVER_ERROR,"发送随机异常"
            );
        }*/
        // 3.将orderAddDTO中的信息转换为Order实体类,然后新增到数据库中
        Order order=new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        // 执行新增
        orderMapper.insertOrder(order);
        log.info("新增订单信息为:{}",order);
    }
}







