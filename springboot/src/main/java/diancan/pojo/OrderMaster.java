package diancan.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单主表
 * Created by Administrator on 2017/10/14 0014.
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    /**订单id*/
    private String orderId;

    /**买家姓名*/
    private String buyerName;

    /**买家手机号码*/
    private String buyerPhone;

    /**买家送货地址*/
    private String buyerAddress;

    /**买家微信openid*/
    private String buyerOpenid;

    /**订单总额*/
    private double orderAmount;

    /**订单状态*/
    private Integer orderStatus = 0;

    /**支付状态*/
    private Integer payStatus = 0;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

}
