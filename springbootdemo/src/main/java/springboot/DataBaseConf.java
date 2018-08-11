package springboot;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
public class DataBaseConf implements EnvironmentAware {

    private Environment environment;
    private RelaxedPropertyResolver datasourcePropertyResolver
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.datasourcePropertyResolver = new RelaxedPropertyResolver(environment,"spring.datasource");
    }
    @Bean
    public DataSource dataSource() throws SQLException{
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(datasourcePropertyResolver.getProperty("url"));
        druidDataSource.setUsername(datasourcePropertyResolver.getProperty("username"));
        druidDataSource.setPassword(datasourcePropertyResolver.getProperty("pasword"));
        return druidDataSource;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean()throws SQLException{
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(this.dataSource());
        Process
    }
}
