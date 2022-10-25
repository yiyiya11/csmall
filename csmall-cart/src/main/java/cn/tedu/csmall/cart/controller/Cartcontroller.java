package cn.tedu.csmall.cart.controller;

import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "购物车管理模块")
@RestController
@RequestMapping("/base/cart")
public class Cartcontroller {

    @Autowired
    ICartService service;

    @ApiOperation("添加购物车")
    @PostMapping("/add")
    public JsonResult cartAdd(CartAddDTO cartAddDTO){
        service.cartAdd(cartAddDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除购物车")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户ID",name = "userId",example = "UU100"),
            @ApiImplicitParam(value = "商品编号",name = "commodityCode",example = "PC100"),
    })
    public JsonResult deleteUserCart(String userId,String commodityCode){
        service.deleteUserCart(userId,commodityCode);
        return JsonResult.ok();
    }
}
