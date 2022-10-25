package cn.tedu.csmall.cart.service;

import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;

public interface ICartService {

    /**
     *  新增购物车业务逻辑层方法
     * @param cartAddDTO
     */
    void cartAdd(CartAddDTO cartAddDTO);

    /**
     * 删除购物车
     * @param userId
     * @param commodityCode
     */
    void deleteUserCart(String userId,String commodityCode);
}
