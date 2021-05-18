package com.ct.springsecurity.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean accountNonExpired;          // 是否没过期
    private boolean accountNonLocked;           // 是否没被锁定
    private boolean credentialsNonExpired;      // 凭证(密码)是否没过期
    private boolean enabled;                    // 账号是否可用
    private Collection<? extends GrantedAuthority> authorities;     // 用户的权限集合

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {   // 数据库中没有定义该字段，所以直接返回true.有的话再改回来
        this.accountNonExpired = true;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {   // 数据库中没有定义该字段，所以直接返回true.有的话再改回来
        this.accountNonLocked = true;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {   // 数据库中没有定义该字段，所以直接返回true.有的话再改回来
        this.credentialsNonExpired = true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}
