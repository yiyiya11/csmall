package cn.tedu.csmall.business.controller;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("base/business")
@Api(tags = "业务触发模块")
public class BusinessController {
    @Autowired
    private IBusinessService businessService;


    @SentinelResource(
            value = "调用方法",
            blockHandler = "blockError",
            fallback = "fallbackError"
    )
    @PostMapping("/buy")
    public JsonResult buy(OrderAddDTO orderAddDTO){
        businessService.buy(orderAddDTO);
        if (Math.random()<0.5){
            throw new RuntimeException("你在这里错了");
        }
        return JsonResult.ok("你进来啦，哈哈哈你好棒");
    }

    public JsonResult blockError(OrderAddDTO orderAddDTO, BlockException e){
        //给出被限流的提示
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,
                "你丫有病发这么快的请求干嘛");
    }

    public JsonResult fallbackError(OrderAddDTO orderAddDTO,Throwable throwable){
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,throwable.getMessage());
    }
}
