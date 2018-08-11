package pinyougou.core.serviceinterface;

import entity.PageResult;
import pinyougou.core.pojo.good.Goods;
import pinyougou.core.pojo.goodspojo.GoodsVo;

public interface GoodsService {
    void updata(GoodsVo goodsVo);

    void addGoods(GoodsVo goodsVo);

    PageResult findlist(Integer pageNum, Integer pageSize, Goods goods);

    GoodsVo findOne(Long id);

    void updataStatus(Long[] ids, String status);

    void delete(Long[] ids);
}
