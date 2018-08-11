package pinyougou.core.pojo.goodspojo;

import pinyougou.core.pojo.good.Goods;
import pinyougou.core.pojo.good.GoodsDesc;
import pinyougou.core.pojo.item.Item;

import java.io.Serializable;
import java.util.List;

public class GoodsVo implements Serializable {
    private Goods goods;//spu 标准商品单元，一个商品的属性
    private List<Item> itemList;//sku 库存单位
    private GoodsDesc goodsDesc; //商品扩展信息

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public GoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(GoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}
