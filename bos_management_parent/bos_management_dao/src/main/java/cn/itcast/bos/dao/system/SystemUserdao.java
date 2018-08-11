package cn.itcast.bos.dao.system;

import cn.itcast.bos.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserdao extends JpaRepository<User,Integer>{
    User findByUsername(String username);
}
