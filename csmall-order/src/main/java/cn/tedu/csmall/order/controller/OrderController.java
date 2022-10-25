package cn.tedu.csmall.order.controller;

import cn.tedu.csmall.order.service.IOrderService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单管理模块")
@RequestMapping("base/order")
@RestController
public class OrderController {

    @Autowired
    private IOrderService service;

    @ApiOperation("添加订单")
    @PostMapping("/add")
    public JsonResult addOrder(OrderAddDTO orderAddDTO){
        service.orderAdd(orderAddDTO);
        return JsonResult.ok();
    }
}
