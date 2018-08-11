package diancan.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情
 * Created by Administrator on 2017/10/14 0014.
 */
@Entity
@Data
public class OrderDetail {

    @Id
    /**订单详情id*/
    private String detailId;

    /**订单id*/
    private String orderId;

    /**商品id*/
    private String productId; //传入

    /**商品名称*/
    private String productName;

    /**商品单价*/
    private double productPrice;

    /**商品数量*/
    private Integer productQuantity; //传入

    /**商品图标*/
    private String productIcon;
}
