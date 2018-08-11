package pinyougou.core.dao.order;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pinyougou.core.pojo.order.Order;
import pinyougou.core.pojo.order.OrderQuery;

public interface OrderDao {
    int countByExample(OrderQuery example);

    int deleteByExample(OrderQuery example);

    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderQuery example);

    Order selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderQuery example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderQuery example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}