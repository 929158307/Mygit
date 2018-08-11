package cn.itcast.bos.web.action.system;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.system.UserService;
import cn.itcast.bos.utils.Md5Util;
import cn.itcast.bos.web.action.common.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.objectweb.asm.commons.TryCatchBlockSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
@Results({
        @Result(name="login",type="redirect",location="/pages/base/login.jsp")
})
public class UserAction extends BaseAction<User> {
    Iterable
    @Autowired
    private UserService userServiceImpl;
    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    @Action("userAction_login")
    public String login(){
        if(StringUtils.isNotBlank(checkcode)){
            String realCode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
            if(realCode.equals(checkcode)){
                Subject subject = SecurityUtils.getSubject();
                if(subject.isAuthenticated()){
                    return "index";
                }else {
                    AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), Md5Util.encode(model.getPassword()));
                    try {
                        subject.login(token);
                        ServletActionContext.getRequest().getSession().setAttribute("User",(User)subject.getPrincipal());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }


       }
        return "login";
    }
}
