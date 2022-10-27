package cn.tedu.csmall.stock.webapi.controller;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.stock.service.IStockService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.CollateExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BlockingDeque;

@RestController
@RequestMapping("/base/stock")
@Api(tags = "库存管理模块")
public class StockController {


    @Autowired
    private IStockService stockService;


    @PostMapping("/reduce/count")
    @ApiOperation("减少商品库存数")
    //blockHander 制定被限流时，要运行自定义的方法属性
    @SentinelResource(
            value = "减少商品库存数",
            blockHandler = "blockError",
            fallback = "fallbackError"
    )
    public JsonResult reduceCommodityCount(
                        StockReduceCountDTO stockReduceCountDTO) {
        // 调用业务逻辑层
        if (Math.random()<0.5){
            throw new RuntimeException("哈哈哈哈小倒霉蛋你错了吧");
        }
        stockService.reduceCommodityCount(stockReduceCountDTO);
        return JsonResult.ok("库存减少完成!");
    }
//  访问修饰符鄙视 是public 返回类型必须与控制器方法一直，方法名必须和控制器方法注解中的blackHander定义的必须一直
    //方法参数必须必包含控制器的所有参数，并且可以二位添加BkickException的异常类型参数
    public JsonResult blockError(StockReduceCountDTO stockReduceCountDTO, BlockException e){
        //给出被限流的提示
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,
                "你丫有病发这么快的请求干嘛");
    }

    public JsonResult fallbackError(StockReduceCountDTO stockReduceCountDTO,Throwable throwable){
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,throwable.getMessage());
    }

}
