package cn.itcast.core.Mapper;

import cn.itcast.core.pojo.User;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    public void addUser(User user);
    public List<User> findAll();
}
