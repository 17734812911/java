package com.ct.springsecurity.service;

import com.ct.springsecurity.pojo.PersionDemo;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MethodELService{

    // 表示在执行方法之前判断当前用户是不是admin角色,如果不是，抛出一个AccessDeniedException异常
    @PreAuthorize("hasRole('admin')")
    public List<PersionDemo> findAll(){
        return null;
    }

    // 在执行方法之后判断返回的对象的name是不是等于当前登录主体的name。如果相同，就将return的结果返回给
    // 客户端，如果不一样，就抛出一个异常
    @PostAuthorize("returnObject.name == authentication.name")
    public PersionDemo findOne(){
        String authName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(authName);
        return new PersionDemo("admin");	// 返回一个PersonDemo对象，name参数值为admin
    }

    // 用来过滤参数,过滤的规则就是value中定义的规则(如果取模后等于0，就将参数传递给delete方法，如果不等
    // 于0，就将这个id过滤掉)
    @PreFilter(filterTarget="ids",value="filterObject%2==0")
    public void delete(List<Integer> ids, List<String> usernames){
        System.out.println(ids);
    }

    // 方法执行完后，判断返回值中的每一个PersionDemo对象的name是不是和当前登录的用户的name相同
    // 如果不相同就会被过滤掉
    @PostFilter("filterObject.name == authentication.name")
    public List<PersionDemo> findAllPD(){
        List<PersionDemo> list = new ArrayList<>();
        list.add(new PersionDemo("kobe"));
        list.add(new PersionDemo("admin"));
        return list;
    }
}