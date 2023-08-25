package com.emall.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean //注入到IOC容器中
    public DataSource druidDataSource() {

        return new DruidDataSource();
    }

    //配置 Druid 监控管理后台的Servlet
    //访问：http://localhost:8888/druid/login.html
    @Bean
    public ServletRegistrationBean viewServlet() {

        //只要访问这个链接，就会进入后台监控页面！这个页面是帮我们写好的！
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String, String> initParams = new HashMap<>();
        //管理员账号密码配置
        initParams.put("loginUsername", "admin"); //后台管理界面的登录账号
        initParams.put("loginPassword", "123456"); //后台管理界面的登录密码


        //initParams.put("allow", "")：为空或者为null时，表示允许所有访问
        initParams.put("allow", "");
        //deny：后台拒绝谁可以访问，名字我们自定义
        //initParams.put("kuangshen", "192.168.1.20");表示禁止此ip访问

        bean.setInitParameters(initParams);
        return bean;
    }

    //配置 Druid 监控 之  web 监控的 filter
    @Bean
    public FilterRegistrationBean webStatFilter() {

        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        return bean;
    }
}
