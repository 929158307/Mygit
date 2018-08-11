package cn.itheima.service.impl;

import cn.itheima.mapper.UserMapper;
import cn.itheima.pojo.QueryVo;
import cn.itheima.pojo.User;
import cn.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void addUser(QueryVo vo) {
        userMapper.addUser(vo);
    }
    @Override
    public User findUser(int id){
        return userMapper.findUser(id);
    }

}
