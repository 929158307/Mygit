package cn.itcast.core.controller;

import cn.itcast.core.pojo.User;
import cn.itcast.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ListAction {
    @Autowired
    private UserService userService;


    @RequestMapping("/insert")
    public String list(User user) throws Exception {
        userService.addUser(user);
        userService.addIndex();
        return "forward:list.action";
    }
    @RequestMapping("list")
    public List<User> findAll(ModelMap modelMap){
        List<User> list =  userService.findAll();
        modelMap.addAttribute("userList",list);
        return list;
    }

}
