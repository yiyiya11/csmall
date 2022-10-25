package cn.tedu.csmall.stock.service;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;

public interface IStockService {

    // 减少库存的业务逻辑层
    void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO);
}
