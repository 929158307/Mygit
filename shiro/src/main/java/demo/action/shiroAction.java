package demo.action;

import cn.itcast.bos.web.action.common.BaseAction;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Namespace("/")
@Scope("prototype")
@Results({
        @Result(name = "success",location = "index")
})
public class shiroAction extends BaseAction<>{

    @Autowired
    private SecurityManager securityManager;

    @Action("login")
    public String login(){

        return "index";
    }
}
