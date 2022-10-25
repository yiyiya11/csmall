package cn.tedu.csmall.business.controller;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.restful.JsonResult;
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


    @PostMapping("/buy")
    public JsonResult buy(){
        businessService.buy();
        return JsonResult.ok("你进来啦，哈哈哈你好棒");
    }
}
