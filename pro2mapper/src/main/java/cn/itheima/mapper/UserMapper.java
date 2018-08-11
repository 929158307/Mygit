package cn.itheima.mapper;

import cn.itheima.pojo.QueryVo;
import cn.itheima.pojo.User;

public interface UserMapper {
    void addUser(QueryVo vo);
    User findUser(int id);
}
