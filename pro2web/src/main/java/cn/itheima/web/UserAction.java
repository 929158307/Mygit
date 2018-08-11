package cn.itheima.web;

import cn.itheima.pojo.QueryVo;
import cn.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAction {
    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("addUser")
    public String addUser(QueryVo vo){
        userServiceImpl.addUser(vo);
        return "success";
    }
    // @RequestMapping("findUser/value")并且把纸赋给 value
    //public void find(@PathVariable("value") int value)
    //将这个value 给value赋值上
    @RequestMapping("findUser")
    public String findUser(QueryVo vo){
        int id = vo.getUser().getId();
        System.out.println(userServiceImpl.findUser(id));
        return "success";
    }
}
