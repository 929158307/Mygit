package cn.itcast.bos.web.action.realms;

import cn.itcast.bos.dao.system.SystemUserdao;
import cn.itcast.bos.domain.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//查询安全数据
@Component("bosRealm")
public class BosRealms extends AuthorizingRealm {
    @Autowired
    private SystemUserdao userdao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        User user = userdao.findByUsername(username);
        if(user == null){
            return  null;
        }
        //参数一 主角信息，参数2 数据库里面的密码
        AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        return info;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object primaryPrincipal = principals.getPrimaryPrincipal();
        User user = (User) primaryPrincipal;
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        info.addRoles(info.getRoles());
        info.addStringPermission("666");
        return info;
    }
}
