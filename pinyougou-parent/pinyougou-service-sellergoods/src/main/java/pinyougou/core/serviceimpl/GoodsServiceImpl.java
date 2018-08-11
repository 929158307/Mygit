package pinyougou.core.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.core.context.SecurityContextHolder;
import pinyougou.core.dao.good.BrandDao;
import pinyougou.core.dao.good.GoodsDao;
import pinyougou.core.dao.good.GoodsDescDao;
import pinyougou.core.dao.item.ItemCatDao;
import pinyougou.core.dao.item.ItemDao;
import pinyougou.core.dao.seller.SellerDao;
import pinyougou.core.pojo.good.Goods;
import pinyougou.core.pojo.good.GoodsDesc;
import pinyougou.core.pojo.good.GoodsDescQuery;
import pinyougou.core.pojo.good.GoodsQuery;
import pinyougou.core.pojo.goodspojo.GoodsVo;
import pinyougou.core.pojo.item.Item;
import pinyougou.core.pojo.item.ItemCat;
import pinyougou.core.pojo.item.ItemQuery;
import pinyougou.core.pojo.seller.Seller;
import pinyougou.core.pojo.seller.SellerQuery;
import pinyougou.core.serviceinterface.GoodsService;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsDescDao goodsDescDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private ItemCatDao itemCatDao;

    @Autowired
    private JmsTemplate jmsTemplate;
    @Override
    public void updata(GoodsVo goodsVo) {
        goodsDao.updateByPrimaryKeySelective((goodsVo.getGoods()));
        goodsDescDao.updateByPrimaryKeySelective(goodsVo.getGoodsDesc());
        ItemQuery itemQuery = new ItemQuery();
        itemQuery.createCriteria().andGoodsIdEqualTo(goodsVo.getGoods().getId());
        itemDao.deleteByExample(itemQuery);
        //添加item
        if ("1".equals(goodsVo.getGoods().getIsEnableSpec())) {
            //库存表存n跳数据
            List<Item> itemList = goodsVo.getItemList();
            for (Item item : itemList) {
                //商品sku 名字
                String title = goodsVo.getGoods().getGoodsName();
                //规格描述串 比如 颜色 对应  型号对应， 是一个string 可以吧这个json拆成 map json.parseArray是拆成 list
                String spec = item.getSpec();
                //将描述穿拆成一个map 这个map里面有大量的键值对 关于规格的
                Map map = JSON.parseObject(spec, Map.class);
                Set<Map.Entry<String, String>> set = map.entrySet();
                for (Map.Entry<String, String> entry : set) {
                    title = " " + entry.getValue();
                }
                item.setTitle(title);
                setItemValue(item, goodsVo);
                itemDao.insertSelective(item);
            }
        } else { //不启用 没有规格 以为名字就是SKU名字
            Item item = new Item();
            item.setTitle(goodsVo.getGoods().getGoodsName());
            item.setPrice(goodsVo.getGoods().getPrice());
            item.setStatus("1");
            item.setIsDefault("1");
            item.setNum(99999);
            item.setSpec("{}");
            setItemValue(item, goodsVo);
            itemDao.insertSelective(item);
        }
    }
    @Override
    public void addGoods(GoodsVo goodsVo){
        //设置seller id
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        SellerQuery sellerQuery = new SellerQuery();
        sellerQuery.createCriteria().andNameLike(name);
        List<Seller> sellers = sellerDao.selectByExample(sellerQuery);
        goodsVo.getGoods().setSellerId(sellers.get(0).getSellerId());

        //设置商品未审核
        goodsVo.getGoods().setAuditStatus("0");
        //保存商品
        goodsDao.insertSelective(goodsVo.getGoods());
        //存储额外信息 设置额外信息属于谁
        goodsVo.getGoodsDesc().setGoodsId(goodsVo.getGoods().getId());
        //存储额外信息
        goodsDescDao.insertSelective(goodsVo.getGoodsDesc());
        //是否启用规格 启用规格 会有很多item 以及 item的描述为 商品SKU + 规格描述穿
        if("1".equals(goodsVo.getGoods().getIsEnableSpec())){
            //库存表存n跳数据
            List<Item> itemList = goodsVo.getItemList();
            for(Item item : itemList){
                //商品sku 名字
                String title = goodsVo.getGoods().getGoodsName();
                //规格描述串 比如 颜色 对应  型号对应， 是一个string 可以吧这个json拆成 map json.parseArray是拆成 list
                String spec = item.getSpec();
                //将描述穿拆成一个map 这个map里面有大量的键值对 关于规格的
                Map map = JSON.parseObject(spec, Map.class);
                Set<Map.Entry<String,String>> set = map.entrySet();
                for(Map.Entry<String,String> entry : set) {
                    title = " " + entry.getValue();
                }
                item.setTitle(title);
                setItemValue(item,goodsVo);
                itemDao.insertSelective(item);
            }
        }else{ //不启用 没有规格 以为名字就是SKU名字
            Item item = new Item();
            item.setTitle(goodsVo.getGoods().getGoodsName());
            item.setPrice(goodsVo.getGoods().getPrice());
            item.setStatus("1");
            item.setIsDefault("1");
            item.setNum(99999);
            item.setSpec("{}");
            setItemValue(item,goodsVo);
            itemDao.insertSelective(item);
        }
    }
    public void setItemValue(Item item,GoodsVo goodsVo){
        //图片
        List<Map> list = JSON.parseArray(goodsVo.getGoodsDesc().getItemImages(), Map.class);
        if(list.size()>0&&null!=list){
            item.setImage((String) list.get(0).get("url"));
        }
        //商品SPU编号
        item.setGoodsId(goodsVo.getGoods().getId());
        //商家编号
        item.setSellerId(goodsVo.getGoods().getSellerId());
        //商品编号分类
        itemDao.insertSelective(item);
        //商品三级分类
        item.setCategoryid(goodsVo.getGoods().getCategory3Id());
        //修改日期&更新日期
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());
        //品牌名称
        item.setBrand(brandDao.selectByPrimaryKey(goodsVo.getGoods().getBrandId()).getName());
        //店铺名称
        item.setSeller(sellerDao.selectByPrimaryKey(goodsVo.getGoods().getSellerId()).getNickName());
        //分类名称
        item.setCategory(itemCatDao.selectByPrimaryKey(goodsVo.getGoods().getCategory3Id()).getName());
    }
    @Override
    public PageResult findlist(Integer pageNum, Integer pageSize, Goods goods){
        PageHelper.startPage(pageNum,pageSize);
        GoodsQuery goodsQuery = new GoodsQuery();
        GoodsQuery.Criteria criteria = goodsQuery.createCriteria();
        if(null!=goods.getAuditStatus() && !"".equals(goods.getAuditStatus())) {
            criteria.andAuditStatusEqualTo(goods.getAuditStatus());
        }
        if(null!=goods.getGoodsName() && !"".equals(goods.getGoodsName())){
            criteria.andGoodsNameLike(goods.getGoodsName());
        }
        criteria.andIsDeleteIsNull();
        Page<Goods> goodsPage =  (Page<Goods>)goodsDao.selectByExample(goodsQuery);
        return new PageResult(goodsPage.getTotal(),goodsPage.getResult());
    }
    @Override
    public GoodsVo findOne(Long id){
        Goods goods = goodsDao.selectByPrimaryKey(id);
        GoodsDescQuery goodsDescQuery = new GoodsDescQuery();
        goodsDescQuery.createCriteria().andGoodsIdEqualTo(id);
        GoodsDesc goodsDesc = goodsDescDao.selectByExample(goodsDescQuery).get(0);
        ItemQuery itemQuery = new ItemQuery();
        ItemQuery.Criteria criteria = itemQuery.createCriteria();
        criteria.andGoodsIdEqualTo(id);
        List<Item> items = itemDao.selectByExample(itemQuery);
        GoodsVo goodsVo = new GoodsVo();
        goodsVo.setGoods(goods);
        goodsVo.setGoodsDesc(goodsDesc);
        goodsVo.setItemList(items);
        return goodsVo;
    }

    @Override
    public void updataStatus(Long[] ids, String status) {
        Goods goods = new Goods();
        goods.setAuditStatus(status);
        for(Long id : ids){
            goods.setId(id);
            goodsDao.updateByPrimaryKeySelective(goods);

            //TODO 将商品信息保存到索引库
            //TODO 静态化
        }
    }
    @Override
    public void delete(Long[] ids){
        Goods goods = new Goods();
        goods.setIsDelete("1");
        for(Long id : ids){
            goods.setId(id);
            goodsDao.updateByPrimaryKeySelective(goods);

            //TODO 删索引
            //TODO 静态化
        }
    }


//    public void sendMessage(){
//        jmsTemplate.send("pinyougou_topic_page_solr", new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//
//                return ;
//            }
//        });
//    }
//    public void jieshou(){
//        Message sss = jmsTemplate.receive("sss");
//        sss.g
//    }
}
