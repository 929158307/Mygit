package cn.itcast.core.service;

import cn.itcast.core.pojo.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);

    void addIndex();

    List<User> findAll();
}
