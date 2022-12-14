package cn.tedu.csmall.cart.webapi.service.impl;

import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.cart.webapi.mapper.CartMapper;
import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.pojo.cart.model.Cart;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@DubboService
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper mapper;

    @Override
    public void cartAdd(CartAddDTO cartAddDTO) {
        Cart cart=new Cart();
        BeanUtils.copyProperties(cartAddDTO,cart);
        mapper.insertCart(cart);
        log.info("新增购物车完成！{}",cart);
    }

    @Override
    public void deleteUserCart(String userId, String commodityCode) {
        mapper.deleteCartByUserIdAndCommodityCode(userId,commodityCode);
    }
}
