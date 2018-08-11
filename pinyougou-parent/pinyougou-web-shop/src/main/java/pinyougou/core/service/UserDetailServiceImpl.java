package pinyougou.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pinyougou.core.pojo.seller.Seller;
import pinyougou.core.serviceinterface.SellerService;

import javax.annotation.Generated;
import java.util.HashSet;
import java.util.Set;

@Service(value = "userDetailService")
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    @Generated("sellerService")
    private SellerService sellerService;

//    private SellerService sellerService;
//    public void setSellerService(SellerService sellerService) {
//        this.sellerService = sellerService;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Seller seller = sellerService.findByName(username);
        if(!"0".equals(seller.getStatus().trim())){
            //审核通过
            //权限设置
            Set<GrantedAuthority> Authority = new HashSet<>();
            Authority.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            return new User(username,seller.getPassword(),Authority);
        }else {
            System.out.println("审核未通过");
        }
        return null;
    }


}
