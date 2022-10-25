package cn.tedu.csmall.order.mapper;

import cn.tedu.csmall.commons.pojo.order.model.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    @Insert("insert into order_tbl(user_id, commodity_code, count, money) VALUES (#{userId},#{commodityCode},#{count},#{money})")
    int insertOrder(Order order);

    @Delete("delete from order_tbl where user_id=#{userId} and commodity_code=#{commodityCode}")
    int deleteOrder(String userId,String commodityCode);
}
