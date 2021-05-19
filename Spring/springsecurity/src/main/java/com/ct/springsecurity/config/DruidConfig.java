<<<<<<< HEAD
package com.ct.springsecurity.config;
=======
package com.ct.config;
>>>>>>> cafa1c3d396dec85d499e6362033306f869edca8

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
<<<<<<< HEAD
=======

>>>>>>> cafa1c3d396dec85d499e6362033306f869edca8
    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置Druid监控
    //1、配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean<Servlet> statViewServlet(){
        ServletRegistrationBean<Servlet> bean = new ServletRegistrationBean<Servlet>(new StatViewServlet(),"/druid/*");
        Map<String,String> initparams = new HashMap<String,String>();
        initparams.put("loginUsername", "admin");
        initparams.put("loginPassword", "123456");
        initparams.put("allow", "");	//白名单,默认就是允许所有访问
        initparams.put("deny", "192.168.100.45");	//黑名单
        bean.setInitParameters(initparams);
        return bean;
    }

    //2、配置一个监控的filter
    @Bean
    public FilterRegistrationBean<Filter> webStatFilter(){
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<Filter>(new WebStatFilter());
        Map<String,String> initparams = new HashMap<String,String>();
        initparams.put("exclusions", "*.js,*.css,/druid/*");	//不拦截的资源请求
        filter.setInitParameters(initparams);
        filter.setUrlPatterns(Arrays.asList("/*"));	//拦截哪些请求,这里是拦截所有请求
        return filter;
    }

    //访问http://localhost:8080/druid
}
