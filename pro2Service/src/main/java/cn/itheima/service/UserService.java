package cn.itheima.service;

import cn.itheima.pojo.QueryVo;
import cn.itheima.pojo.User;

public interface UserService {
    void addUser(QueryVo vo);
    User findUser(int id);
}
