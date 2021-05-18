package com.ct.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Session超时或被踢下线时的自定义处理策略类
 */
@Component
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    // // 页面跳转的处理类
    // private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    // 前后端分离时，需要返回的是JSON数据，不需要进行上面的页面跳转
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {

        // // 用RedirectStrategy对象的sendRedirect()方法进行页面跳转
        // redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), "/login.html");

        // 向前端返回JSON数据
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",403);
        // 在msg后面加上上一次请求的发送时间
        map.put("msg","账号在别处登录，被迫下线 " + event.getSessionInformation().getLastRequest());
        // 将数据转换为JSON对象
        String json = objectMapper.writeValueAsString(map);
        // 向浏览器写回数据
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(json);
    }
}
