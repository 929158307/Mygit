package diancan.vo;

import diancan.pojo.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderVo {

        /**订单id*/
        private String orderId;

        /**
         * 买家姓名
         */
        private String buyerName;

        /**
         * 买家手机号码
         */
        private String buyerPhone;

        /**
         * 买家送货地址
         */
        private String buyerAddress;

        /**
         * 买家微信openid
         */
        private String buyerOpenid;

        /**
         * 订单总额
         */
        private double orderAmount;

        /**订单状态*/
        private Integer orderStatus;
        /**
         * 创建时间
         */
        private Date createTime;

        /**支付状态*/
        private Integer payStatus = 0;

        /**
         * 更新时间
         */
        private Date updateTime;

        private List<OrderDetail>  orderDetailList;

}
