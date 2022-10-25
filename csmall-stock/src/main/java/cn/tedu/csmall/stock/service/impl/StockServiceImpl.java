package cn.tedu.csmall.stock.service.impl;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.stock.mapper.StockMapper;
import cn.tedu.csmall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockServiceImpl implements IStockService {
    @Autowired
    private StockMapper stockMapper;
    @Override
    public void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO) {
        // 调用减少库存的业务逻辑层方法
        //  updateStockCount([商品编号],[减少的库存数])
        int row=stockMapper.updateStockCount(
                stockReduceCountDTO.getCommodityCode(),
                stockReduceCountDTO.getReduceCount());
        // 可以判断row是否为0\1输出库存修改成功或失败的信息
        log.info("库存减少完成");
    }
}
